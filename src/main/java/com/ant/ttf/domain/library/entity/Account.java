package com.ant.ttf.domain.library.entity;

import java.time.LocalDateTime;

import com.ant.ttf.domain.library.dto.response.LibTotalResDTO;

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
public class Account {
	
	private Long accountId; 
	private Long userId;
	private Long bankInfo;
	private String nickname;
	private String type; 
	private LocalDateTime createdAt;
	private String accNum; 
	private int balance;
	private int accCk;
	
	public LibTotalResDTO convertDTO(Account userAcc) {
		
		return LibTotalResDTO.builder()
				.accountId(userAcc.getAccountId())
				.accNum(userAcc.getAccNum())
				.type(userAcc.getType())
				.accCk(userAcc.getAccCk())
				.nickname(userAcc.getNickname())
				.balance(userAcc.getBalance())
				.build();			
	}
	
}
