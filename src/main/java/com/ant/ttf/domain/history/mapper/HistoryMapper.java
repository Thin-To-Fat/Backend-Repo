package com.ant.ttf.domain.history.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ant.ttf.domain.history.dto.response.CategoryExpendsDTO;
import com.ant.ttf.domain.history.dto.response.MonthlyPriceDTO;
import com.ant.ttf.domain.history.dto.response.TodayStateInfoDTO;

@Mapper
public interface HistoryMapper {
	List<CategoryExpendsDTO> findCategoryExpend(String userPk);
	TodayStateInfoDTO findTodayExpend(String userPk);
	String expectMonthlyFixedPrice(String userPk);
	List<MonthlyPriceDTO> findMonthlyExpend(String userPk);
	double findMonthExpend(HashMap info);
	int findCafeExpendCount(String userPk);
	List<Map> findCategoryAll();
	List<Map> findUserAccountAll(String userPk);
	List<Map> findUserAllAccountAll(Map alllist);
	List<Map> findUserAcctStatistic(String userPk, String nowdate);
	List<Map> findUserdailyAcctStatistic(String userPk, String nowdate);
	List<Map> findUserDailyOut(String userPk, String nowdate);
	List<Map> findUserDailyIn(String userPk, String nowdate);
	List<Map> top3List(String userPk);
	int depleteBudget(String userPk);
}

