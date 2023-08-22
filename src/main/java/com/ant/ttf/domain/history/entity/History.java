package com.ant.ttf.domain.history.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class History {
	private Long history_id;
	private Long account_id;
	private Long user_id;
	private Long category_id;
	private String pay_time;
	private String detail;
	private int price;
	private int price_ck;
	private int fixed;
	
}
