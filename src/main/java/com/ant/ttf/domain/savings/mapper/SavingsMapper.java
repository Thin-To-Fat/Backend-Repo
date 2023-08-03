package com.ant.ttf.domain.savings.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SavingsMapper {
	List<Map> findBankInfo();
	
	List<Map> findSavingsList(Map param);
}
