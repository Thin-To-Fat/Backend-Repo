package com.ant.ttf.domain.history.Controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ant.ttf.domain.history.dto.response.CategoryExpendsDTO;
import com.ant.ttf.domain.history.dto.response.MonthlyPriceDTO;
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
	HistoryService historyService;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	HistoryMapper histoyMapper;
	
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
	
	// 매달 예상 고정 지출 금액 가져오는 API
	@GetMapping("/dashboard/fixed")
	public ResponseEntity<ResponseFormat<String>> monthlyFixedPrice(@RequestHeader("X-AUTH-TOKEN") String token) throws Exception{
		String fixed = historyService.fixedPrice(token);
		ResponseFormat<String> responseFormat = new ResponseFormat<>(ResponseStatus.DASHBOARD_GET_HISTORYFIXED_SUCCESS, fixed);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	// 달별 올해 지출 기록 가져오는 API
	@GetMapping("/dashboard/months")
	public ResponseEntity<ResponseFormat<List<MonthlyPriceDTO>>> monthlyPrice(@RequestHeader("X-AUTH-TOKEN") String token) throws Exception{
		List<MonthlyPriceDTO> monPrice = historyService.getMonPriceList(token);
		ResponseFormat<List<MonthlyPriceDTO>> responseFormat = new ResponseFormat<>(ResponseStatus.DASHBOARD_GET_HISTORYCATEGORY_SUCCESS, monPrice);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}

	//존재하는 필터 카테고리 정보를 가져오는API
	@GetMapping("/categoryInfo")
	public ResponseEntity<ResponseFormat<List<Map>>> getCategoryAll() throws Exception{
		
		List<Map> categories = histoyMapper.findCategoryAll();
		ResponseFormat<List<Map>> responseFormat = new ResponseFormat<>(ResponseStatus.HISTORY_GET_FILTERCATEGORYELEMENT_SUCCESS, categories);
		
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	//유저의 계좌 정보를 가져오는 API
	@GetMapping("/userAccountInfo")
	public ResponseEntity<ResponseFormat<List<Map>>> getUserAccountInfo(@RequestHeader("X-AUTH-TOKEN") String token) throws Exception{
		
		String userPk = jwtTokenProvider.getUserPk(token);
		List<Map> acctinfo = histoyMapper.findUserAccountAll(userPk);
		ResponseFormat<List<Map>> responseFormat = new ResponseFormat<>(ResponseStatus.HISTORY_GET_FILTERACCOUNTELEMENT_SUCCESS, acctinfo);
		
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	//유저의 모든 지출내역 정보를 가져오는 API
	@GetMapping("/userAllAccountInfo")
	public ResponseEntity<ResponseFormat<List<Map>>> getUserAllAccountInfo(@RequestHeader("X-AUTH-TOKEN") String token, @RequestParam Map alllist) throws Exception{
		
		System.out.println(alllist);
		String userPk = jwtTokenProvider.getUserPk(token);

		alllist.put("userPk",userPk);

		List<Map> acctinfo = histoyMapper.findUserAllAccountAll(alllist);
		ResponseFormat<List<Map>> responseFormat = new ResponseFormat<>(ResponseStatus.HISTORY_GET_FILTERDATA_SUCCESS, acctinfo);
		
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	//유저의 모든 지출내역 정보를 가져오는 API
	@GetMapping("/info")
	public ResponseEntity<ResponseFormat<List<Map>>> getUserAcctStatistic(@RequestHeader("X-AUTH-TOKEN") String token, @RequestParam String nowdate) throws Exception{
		String userPk = jwtTokenProvider.getUserPk(token);

		List<Map> acctstatistic = histoyMapper.findUserAcctStatistic(userPk, nowdate);
		
		ResponseFormat<List<Map>> responseFormat = new ResponseFormat<>(ResponseStatus.HISTORY_GET_HISTORYHEADINFO_SUCCESS, acctstatistic);
		
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	//유저의 한달 모든 수입과 지출 리스트로 담는 API
	@GetMapping("/dailyinout")
	public ResponseEntity<ResponseFormat<Map<String, Object>>> getDailyInOut(@RequestHeader("X-AUTH-TOKEN") String token, @RequestParam String nowdate) throws Exception{
		String userPk = jwtTokenProvider.getUserPk(token);
		List<Integer> dailyoutlist = new ArrayList<Integer>();
		List<Integer> dailyInList = new ArrayList<Integer>();

		List<Map> dailyout = histoyMapper.findUserDailyOut(userPk, nowdate);
		List<Map> dailyIn = histoyMapper.findUserDailyIn(userPk, nowdate);
		String[] daylist = nowdate.split("-");
		Integer day = Integer.parseInt(daylist[1]);
		for (int i = 0; i < day; i++) {
		    dailyoutlist.add(0);
		    dailyInList.add(0);
		}
		for (Map<String, Object> map : dailyIn) {
		    if (map.containsKey("dailyindate")) {
		        BigDecimal value = (BigDecimal) map.get("dailyin");
		        BigInteger index = (BigInteger) map.get("dailyindate");
		        dailyInList.set(index.intValue()-1, value.intValue());
		        System.out.println(index.intValue() -1);
		        System.out.println(value.intValue());
		    }
		}
		for (Map<String, Object> map : dailyout) {
		    if (map.containsKey("dailyoutdate")) {
		        BigDecimal value = (BigDecimal) map.get("dailyout");
		        BigInteger index = (BigInteger) map.get("dailyoutdate");
		        if (index.intValue() - 1 <day) {
		        	dailyoutlist.set(index.intValue()-1, value.intValue());	
		        }
		        else {
		        	break;
		        }
		    }
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("dayIncome", dailyInList);
		resultMap.put("dayChange", dailyoutlist);
		resultMap.put("dayIncomeMax", Collections.max(dailyInList));
		resultMap.put("dayChangeMax", Collections.max(dailyoutlist));

		ResponseFormat<Map<String, Object>> responseFormat = new ResponseFormat<>(ResponseStatus.HISTORY_GET_HISTORYTAILINFO_SUCCESS, resultMap);

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

