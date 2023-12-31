package com.ant.ttf.domain.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.ttf.domain.users.dto.request.UsersLoginReqDTO;
import com.ant.ttf.domain.users.dto.request.UsersRequestDTO;
import com.ant.ttf.domain.users.dto.response.UserDashboardInfoDTO;
import com.ant.ttf.domain.users.dto.response.UserTitleResponseDTO;
import com.ant.ttf.domain.users.dto.response.UsersLoginResponseDTO;
import com.ant.ttf.domain.users.mapper.UsersMapper;
import com.ant.ttf.domain.users.service.UsersService;
import com.ant.ttf.global.ResponseFormat;
import com.ant.ttf.global.ResponseStatus;
import com.ant.ttf.global.jwt.service.JwtTokenProvider;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:3000/")
@Log
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class UsersController {
	@Autowired
	UsersService usersService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	UsersMapper userMapper;
	
	// 유저 회원가입 API
	@PostMapping("/signup")
	public ResponseEntity<ResponseFormat<String>> createUser(UsersRequestDTO dto) throws Exception{
		userMapper.userSignUp(dto);
		ResponseFormat<String> responseFormat = new ResponseFormat<>(ResponseStatus.USER_POSTSIGNUP_SUCCESS);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
	}
	
	// 유저 로그인 API
    @PostMapping("/login")
    public ResponseEntity<ResponseFormat<UsersLoginResponseDTO>> login(@RequestBody UsersLoginReqDTO dto) throws Exception{
    	UsersLoginResponseDTO loginDto = usersService.login(dto);
		ResponseFormat<UsersLoginResponseDTO> responseFormat = new ResponseFormat<>(ResponseStatus.USER_POSTLOGIN_SUCCESS, loginDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
    }
    
    // 대시보드 상단 유저 정보 가져오는 API
    @GetMapping("/dashboard")
    public ResponseEntity<ResponseFormat<UserDashboardInfoDTO>> userDashboardInfo(@RequestHeader("X-AUTH-TOKEN") String token) throws Exception{
    	UserDashboardInfoDTO userInfo = usersService.userDshInfo(token);
    	ResponseFormat<UserDashboardInfoDTO> responseFormat = new ResponseFormat<>(ResponseStatus.DASHBOARD_GET_USERINFO_SUCCESS, userInfo);
    	return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
    }
   
    // 대시보드 상단 월 예산 수정하는 API
    @PutMapping("/dashboard/income")
    public ResponseEntity<ResponseFormat<String>> updateIncome(@RequestHeader("X-AUTH-TOKEN") String token, int income) throws Exception{
    	String userPk = jwtTokenProvider.getUserPk(token);
    	userMapper.updateIncome(userPk, income);
    	ResponseFormat<String> responseFormat = new ResponseFormat<>(ResponseStatus.DASHBOARD_PUT_USERINCOME_SUCCESS);
    	return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
    }
    
    // 대시보드 상단 목표 금 수정하는 API
    @PutMapping("/dashboard/goalBudget")
    public ResponseEntity<ResponseFormat<String>> updateGoalBudget(@RequestHeader("X-AUTH-TOKEN") String token, int goalBudget) throws Exception{
    	String userPk = jwtTokenProvider.getUserPk(token);
    	userMapper.updateGoalBudget(userPk, goalBudget);
    	ResponseFormat<String> responseFormat = new ResponseFormat<>(ResponseStatus.DASHBOARD_PUT_USERBUDGET_SUCCESS);
    	return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
    }
    
    // 가챠 API
    @GetMapping("/nickname")
    public ResponseEntity<ResponseFormat<UserTitleResponseDTO>> userTitlegotcha(@RequestHeader("X-AUTH-TOKEN") String token) throws Exception{
    	UserTitleResponseDTO titleInfo = usersService.userTitleGotcha(token);
    	ResponseFormat<UserTitleResponseDTO> responseFormat = new ResponseFormat<>(ResponseStatus.GOTCHA_GET_USERTITLE_SUCCESS, titleInfo);
    	return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
    }
    
    // 출석체크 API
    @PutMapping("/attend")
    public ResponseEntity<ResponseFormat<String>> userAttendance(@RequestHeader("X-AUTH-TOKEN") String token) throws Exception{
    	String userPk = jwtTokenProvider.getUserPk(token);
    	userMapper.userAttend(userPk);
    	ResponseFormat<String> responseFormat = new ResponseFormat<>(ResponseStatus.ATTEND_PUT_POINTUP_SUCCESS);
    	return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
    }
   
}
