package com.ant.ttf.domain.users.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDashboardInfoDTO {
	private String name;
	private int goalBudget;
	private int income;
	private int point;
	private String nickname;
	
}
