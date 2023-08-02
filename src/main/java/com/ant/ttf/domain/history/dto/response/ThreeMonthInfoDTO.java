package com.ant.ttf.domain.history.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class ThreeMonthInfoDTO {
	private double expend3ago;
	private double expend2ago;
	private double expend1ago;
	private double expendTotal;
	private double expendAver;
	private double saving3ago;
	private double saving2ago;
	private double saving1ago;
	private double savingAver;
	private int cafeTimes;
	private double cafeAver;
}
