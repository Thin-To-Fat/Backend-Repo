package com.ant.ttf.domain.library.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateNicknameDTO {
	private String name;
	private long accountPK;	
}
