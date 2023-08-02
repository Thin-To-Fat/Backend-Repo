package com.ant.ttf.domain.history.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.ttf.domain.history.dto.response.CategoryExpendsDTO;
import com.ant.ttf.domain.history.dto.response.ThreeMonthInfoDTO;
import com.ant.ttf.domain.history.dto.response.TodayStateInfoDTO;
import com.ant.ttf.domain.history.dto.response.Top3MonthBudgetDTO;
import com.ant.ttf.domain.history.mapper.HistoryMapper;
import com.ant.ttf.domain.history.service.HistoryService;
import com.ant.ttf.global.ResponseFormat;
import com.ant.ttf.global.ResponseStatus;
import com.ant.ttf.global.jwt.service.JwtTokenProvider;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/v1/history")
@Log
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class HistoryController {
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	HistoryService historyService;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	
	//카테고리별 지출 합계 가져오는 API
	@GetMapping("/dashboard/category")
	public ResponseEntity<ResponseFormat<List<CategoryExpendsDTO>>> categoryExpends(@RequestHeader("X-AUTH-TOKEN") String token) throws Exception{
		List<CategoryExpendsDTO> cateExpends = historyService.getCateExpendsList(token);
		ResponseFormat<List<CategoryExpendsDTO>> responseFormat = new ResponseFormat<>(ResponseStatus.DASHBOARD_GET_HISTORYCATEGORY_SUCCESS, cateExpends);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	// 하루 지출 상태 가져오는 API
	@GetMapping("/dashboard/today")
	public ResponseEntity<ResponseFormat<TodayStateInfoDTO>> todayExpend(@RequestHeader("X-AUTH-TOKEN") String token) throws Exception{
		TodayStateInfoDTO today = historyService.getTodayState(token);
		ResponseFormat<TodayStateInfoDTO> responseFormat = new ResponseFormat<>(ResponseStatus.DASHBOARD_GET_HISTORYTODAY_SUCCESS, today);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	// 3개월기준 지출 정보 가져오는 API
	@GetMapping("/dashboard/threemonth")
	public ResponseEntity<ResponseFormat<ThreeMonthInfoDTO>> threeMonthExpends(@RequestHeader("X-AUTH-TOKEN") String token) throws Exception{
		ThreeMonthInfoDTO info = historyService.getThreeMonthInfo(token);
		ResponseFormat<ThreeMonthInfoDTO> responseFormat = new ResponseFormat<>(ResponseStatus.DASHBOARD_GET_HISTORYTHREEMONTH_SUCCESS, info);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	//Top 3, 예산소지율 가져오는 API
	@GetMapping("/dashboard/top3")
	public ResponseEntity<ResponseFormat<Top3MonthBudgetDTO>> topList(@RequestHeader("X-AUTH-TOKEN") String token) throws Exception{
		
		Top3MonthBudgetDTO result = historyService.getTopListBudget(token);
		ResponseFormat<Top3MonthBudgetDTO> responseFormat = new ResponseFormat<>(ResponseStatus.DASHBOARD_GET_HISTORYTOP3_SUCCESS, result);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}

}

