package com.ant.ttf.domain.ttf.dto.request;

import java.time.LocalDateTime;

import com.ant.ttf.domain.ttf.entity.Ttf;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TtfJoinReqDTO {
	private long accountId;
	private int joinPeriod;
	private int cans;
	
	
	public Ttf ttfForEntity(TtfJoinReqDTO dto, Long userPK2, String newAccNo) {
		//현재시간을 문자열로 변환
		//이 값을 시작날짜에 대입
		LocalDateTime time = LocalDateTime.now();
		String isTime = time.toString();
		
		//시작날짜 + 적금주기 = 만기날짜
		LocalDateTime endT = time.plusMonths(dto.getJoinPeriod());
		String endDate = endT.toString();
		
		return Ttf.builder()
				.userId(userPK2)
				.accountId(dto.getAccountId())
				.startDate(isTime)
				.endDate(endDate)
				.accCk(1)
				.balance(0)
				.accountNum(newAccNo)
				.cans(dto.getCans())
				.build();				
	}
}
