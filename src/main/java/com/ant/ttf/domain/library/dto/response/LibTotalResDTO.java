package com.ant.ttf.domain.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibTotalResDTO {
	
	private Long accountId;
	private String name; // 은행이름
	private String accNum; // 계좌번호
	private String type; //계좌타입
	private int accCk;  // 0: 입출금 계좌, 1: 적금 계좌 2: 신파일러 출금계좌
	private String nickname;
	private int balance;
	
	// 한달동안 사용한 금액, 신파일러출금계좌면 (balance - limitAmount at ttf테이블)
	// 입출금계좌이면, 유저의 월예산 - 통장잔고 at account
	// 적금계좌면 monthBalance는 0
	private int monthBalance; 

	private String imgUrl; //은행 이미지 url
	

}
