package com.ant.ttf.domain.users.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersLoginResponseDTO {
	private String token;
	private String bnplCk;

}
