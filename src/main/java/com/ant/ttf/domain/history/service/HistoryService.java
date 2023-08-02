package com.ant.ttf.domain.history.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ant.ttf.domain.history.dto.response.CategoryExpendsDTO;
import com.ant.ttf.domain.history.dto.response.ThreeMonthInfoDTO;
import com.ant.ttf.domain.history.dto.response.TodayStateInfoDTO;
import com.ant.ttf.domain.history.dto.response.Top3MonthBudgetDTO;

@Service
public interface HistoryService {
	public List<CategoryExpendsDTO> getCateExpendsList(String token);
	public TodayStateInfoDTO getTodayState(String token);
	public ThreeMonthInfoDTO getThreeMonthInfo(String token);
	public Top3MonthBudgetDTO getTopListBudget(String token);
}
