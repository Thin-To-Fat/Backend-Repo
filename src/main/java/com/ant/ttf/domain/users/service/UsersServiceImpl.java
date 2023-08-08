package com.ant.ttf.domain.users.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ant.ttf.domain.users.dto.request.UsersLoginReqDTO;
import com.ant.ttf.domain.users.dto.request.UsersRequestDTO;
import com.ant.ttf.domain.users.dto.response.UserDashboardInfoDTO;
import com.ant.ttf.domain.users.dto.response.UsersLoginResponseDTO;
import com.ant.ttf.domain.users.entity.Users;
import com.ant.ttf.domain.users.mapper.UsersMapper;
import com.ant.ttf.global.exception.LoginFailedException;
import com.ant.ttf.global.exception.UserNotFoundException;
import com.ant.ttf.global.jwt.service.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class UsersServiceImpl implements UsersService {

    private final UsersMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    // 로그인 -> 이메일, 비밀번호 일치 시 토큰 발급
    public UsersLoginResponseDTO login(UsersLoginReqDTO loginDto) {
    	Users userDto = userMapper.findUserByUserEmail(loginDto.getEmail())
                .orElseThrow(() -> new LoginFailedException("잘못된 아이디입니다"));
    	
    	UsersLoginResponseDTO dto = new UsersLoginResponseDTO();

    	log.info(loginDto.getPassword()+ " "+ userDto.getPassword());
    	if (!loginDto.getPassword().equals(userDto.getPassword())) {	
            throw new LoginFailedException("잘못된 비밀번호입니다");
        }

        String token = jwtTokenProvider.createToken(userDto.getUser_id(), Collections.singletonList(userDto.getRole()));
        
        String userPk = jwtTokenProvider.getUserPk(token);
        
        dto.setBnplCk(userMapper.checkBnpl(userPk));
        System.out.println("ck " + userMapper.checkBnpl(userPk));
        
        dto.setToken(token);
        
        return dto;
    }
    
   
    public Users findByUserId(Long userId) {
        return userMapper.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 유저입니다."));
    }
    
    // 대시보드 상단 유저 정보 가져오는 API
	@Override
	public UserDashboardInfoDTO userDshInfo(String token) {
		String userPk = jwtTokenProvider.getUserPk(token);
		Users user = userMapper.findUserInfoByPk(userPk);
		UserDashboardInfoDTO dto = new UserDashboardInfoDTO(); // dto 객체 생성
		dto.setGoalBudget(user.getGoal_budget());
		dto.setName(user.getName());
		dto.setIncome(user.getIncome());
		dto.setPoint(user.getPoint());
		dto.setNickname(user.getNickname());
		return dto;
	}

}