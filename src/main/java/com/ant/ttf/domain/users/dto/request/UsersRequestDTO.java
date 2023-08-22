package com.ant.ttf.domain.users.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequestDTO {
	private String email;
	private String password;
	private String name;
	private String birth;
	
}
