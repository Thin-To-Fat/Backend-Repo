package com.ant.ttf.domain.history.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ant.ttf.domain.history.dto.response.CategoryExpendsDTO;
import com.ant.ttf.domain.history.dto.response.ThreeMonthInfoDTO;
import com.ant.ttf.domain.history.dto.response.TodayStateInfoDTO;
import com.ant.ttf.domain.history.dto.response.Top3MonthBudgetDTO;
import com.ant.ttf.domain.history.mapper.HistoryMapper;
import com.ant.ttf.domain.users.entity.Users;
import com.ant.ttf.domain.users.mapper.UsersMapper;
import com.ant.ttf.global.jwt.service.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class HistoryServiceImpl implements HistoryService {
	
    private final HistoryMapper historyMapper;
    private final UsersMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
	
    //카테고리별 지출내역 리스트
	public List<CategoryExpendsDTO> getCateExpendsList(String token){
		String userPk = jwtTokenProvider.getUserPk(token);
		List<CategoryExpendsDTO> cateExpendsList = historyMapper.findCategoryExpend(userPk); // 카테고리 지출 내역 담은 리스트;
		
		return cateExpendsList;
	}
	
	// 사용자 하루 지출 상태 정보
	public TodayStateInfoDTO getTodayState(String token) {
		String userPk = jwtTokenProvider.getUserPk(token);
		Users userDto = userMapper.findUserInfoByPk(userPk);
		TodayStateInfoDTO today = historyMapper.findTodayExpend(userPk);
		
		int goal = userDto.getGoal_budget();
		Calendar cal = new GregorianCalendar();
        int daysOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        int standard = Math.round(goal/daysOfMonth);
        
        if(today.getDayExpend()<= (standard/2)) {
        	today.setDayState("여유");
        } else if(today.getDayExpend()<=standard) {
        	today.setDayState("양호");
        } else {
        	today.setDayState("위험");
        }
		return today;
	}
	
	// 3개월 기준 지출 상태 정보
	public ThreeMonthInfoDTO getThreeMonthInfo(String token) {
		ThreeMonthInfoDTO result = new ThreeMonthInfoDTO();
		String userPk = jwtTokenProvider.getUserPk(token);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userPk", userPk);
		LocalDate now = LocalDate.now();
		int monthValue = now.getMonthValue();
		log.info("이번달"+monthValue);
		
		
		//카페
		int cafeTime = historyMapper.findCafeExpendCount(userPk);
		result.setCafeTimes(cafeTime);
		result.setCafeAver(Math.round((cafeTime/12)*10)/10.0);
		
		//지출합계
		map.put("month",monthValue-1);
		double expend1ago = historyMapper.findMonthExpend(map);
		result.setExpend1ago(expend1ago);
		map.put("month",monthValue-2);
		double expend2ago = historyMapper.findMonthExpend(map);
		result.setExpend2ago(expend2ago);
		map.put("month",monthValue-3);
		double expend3ago = historyMapper.findMonthExpend(map);
		result.setExpend3ago(expend3ago);
		
		result.setExpendTotal(expend1ago+expend2ago+expend3ago);
		result.setExpendAver(Math.round(((expend1ago+expend2ago+expend3ago)/3)*10)/10.0);
		
		//저축합계
		map.put("category", 11);
		map.put("month",monthValue-1);
		double saving1ago = historyMapper.findMonthExpend(map);
		result.setSaving1ago(saving1ago);
		map.put("month",monthValue-2);
		double saving2ago = historyMapper.findMonthExpend(map);
		result.setSaving2ago(saving2ago);
		map.put("month",monthValue-3);
		double saving3ago = historyMapper.findMonthExpend(map);
		result.setSaving3ago(saving3ago);
		
		result.setSavingAver(Math.round(((saving3ago+saving2ago+saving1ago)/3)*10)/10.0);

		return result;
	}

	@Override
	public Top3MonthBudgetDTO getTopListBudget(String token) {
		ThreeMonthInfoDTO result = new ThreeMonthInfoDTO();
		String userPk = jwtTokenProvider.getUserPk(token);
		List<Map> value  = historyMapper.top3List(userPk);
		int depletedBudget = historyMapper.depleteBudget(userPk);
		Top3MonthBudgetDTO dto = new Top3MonthBudgetDTO();
		
		dto.setTopLists(value);
		dto.setDepletedBudget(depletedBudget);
		
		return dto;
	}
	
}
