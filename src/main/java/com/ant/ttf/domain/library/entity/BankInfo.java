package com.ant.ttf.domain.library.entity;

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
public class BankInfo {
	
	private Long bankId;
	private String name;
	private String imgUrl;
	private int bankClass;
	
}
