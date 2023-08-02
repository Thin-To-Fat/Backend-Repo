package com.ant.ttf.domain.history.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ant.ttf.domain.history.dto.response.CategoryExpendsDTO;
import com.ant.ttf.domain.history.dto.response.TodayStateInfoDTO;

@Mapper
public interface HistoryMapper {
	List<CategoryExpendsDTO> findCategoryExpend(String userPk);
	TodayStateInfoDTO findTodayExpend(String userPk);
	double findMonthExpend(HashMap info);
	int findCafeExpendCount(String userPk);
	List<Map> top3List(String userPk);
	int depleteBudget(String userPk);
}
