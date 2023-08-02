package com.ant.ttf.domain.history.dto.response;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Top3MonthBudgetDTO {
	
	List<Map> topLists;
	int depletedBudget;
	
}
