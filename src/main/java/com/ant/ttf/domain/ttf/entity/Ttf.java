package com.ant.ttf.domain.ttf.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ttf {
	private Long ttfId;
	private Long userId;
	private Long accountId;

	private String startDate;
	private String endDate;
	private int accCk;
	private int balance;
	private int limitAmount;
	private String accountNum;
	private String qrImage;
	private int bnpl;
	private int cans;
}
