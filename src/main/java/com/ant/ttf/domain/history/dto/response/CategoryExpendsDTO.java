package com.ant.ttf.domain.history.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryExpendsDTO {
	private Long uPk;
	private Long cPk;
	private String imgUrl;
	private String name;
	private String color;
	private int price;
	
}
