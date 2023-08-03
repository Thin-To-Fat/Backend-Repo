package com.ant.ttf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ant.ttf.domain.library.dto.response.LibTotalResDTO;
import com.ant.ttf.domain.library.entity.Account;
import com.ant.ttf.domain.library.mapper.LibMapper;
import com.ant.ttf.domain.ttf.dto.request.TtfJoinReqDTO;
import com.ant.ttf.domain.ttf.entity.Ttf;
import com.ant.ttf.domain.ttf.mapper.TtfMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class TtfApplicationTests {
	
	@Autowired
	TtfMapper ttfMapper;
	@Autowired
	LibMapper libMapper;

	@Test
	void contextLoads() {
		
	}
	
	@Test
	void findAccountNum() { // account_id를 이용하여 계좌번호 조회
		
		Long accno = 1l;
		String accString = ttfMapper.findAccountNum(accno);
		
		System.out.println("어카운트넘버 :"+accString);
		log.info("계좌번호조회 :"+accString);
		
		
//		LocalDateTime time = LocalDateTime.now();
//		LocalDateTime newT = time.plusMonths(6);
//		
//		System.out.println("현재시간 :" + newT);
		
	}
	
	@Test
	void joinTtf() { //신파일러 적금계좌 가입 test
		
		String userPK = "1";

		TtfJoinReqDTO dto = new TtfJoinReqDTO();
		dto.setAccountId(1l);
		dto.setJoinPeriod(3);
		dto.setCans(0);
				
		String accNum = ttfMapper.findAccountNum(dto.getAccountId());
		
		
		Long userPK2 = Long.parseLong(userPK);
		
		Ttf ttf = dto.ttfForEntity(dto, userPK2, accNum);
		
		System.out.println(ttf.getAccountNum());
		
		int success = ttfMapper.regTtf(ttf);

		log.info("신파일러 가입 성공" + success);
	}
	
	@Test
	void seeAmount() { //한도가능 금액 조회
		String userPK = "1";
		int amount = ttfMapper.seeLimit(userPK);
		log.info("사용가능금액은?" + amount);
	}
	
	@Test
	void updateLimit() { //결제하고 남은돈 한도가능금액에 업데이트하기
		String userPK = "1";
		int limitBalance = 10000;
		String product_price = "6000";
		
		int productPrice = Integer.parseInt(product_price);
		
		boolean a;
		
		if(limitBalance < productPrice) {
			log.info("사용가능한도 초과");
			a = false;
		} else {
			limitBalance = limitBalance - productPrice;
			Map map = new HashMap();
			map.put("userPK", userPK);
			map.put("limitBalance", limitBalance);
			
			int success = ttfMapper.balUpdate(map);
			log.info("결제가 성공이면 1 : " + success);
			a = true;
		}
		
		log.info("성공 :" + a);
			
	}
	
	
	@Test
	void monthIncome() { // 월예산 조회
		String userPK = "1";
		int month = libMapper.monthIncome(userPK);
		String month2 = Integer.toString(month);
		log.info("월예산:"+month2);
		
	}
	
	@Test
	void testjim() {
		String userPK = "1";
		String bnpl = "10";
		Map map = new HashMap();
		map.put("userPK", userPK);
		map.put("bnpl", bnpl);
		int success = ttfMapper.updateBnpl(map);
		System.out.println("성공이면 1이다 :" + success);
		
	}
	
	

}
