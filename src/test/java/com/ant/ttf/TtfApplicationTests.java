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
		List<LibTotalResDTO> dtoList = new ArrayList<>();
		String userPK = "1";
		List<Account> list = new ArrayList<>(); 
		
		
		
		
		list = libMapper.listAcc(userPK); // 특정 유저에 속한 모든 계좌를 list로 받는다.
		
		for(Account userAcc : list) {
			
			LibTotalResDTO dto = new LibTotalResDTO();
			
			//빌더이용해서 dto에 필요한 정보 넣기(from account 테이블)
			dto = userAcc.convertDTO(userAcc);
			
			
			//bankinfo 테이블에서 은행이름과 이미지url 정보출력
			String bankName = libMapper.nameImg(userAcc.getBankInfo()).getName();
			
			String urlinfo1 = libMapper.nameImg(userAcc.getBankInfo()).getImgUrl();
			System.out.println("오류" + urlinfo1);
			dto.setName(bankName); //dto에 은행이름 넣기
			dto.setImgUrl(urlinfo1); // dto에 은행uri 넣기
			
			
			
			if(userAcc.getAccCk() == 0) { 
				// 입출금계좌이면 dto의 monthBalance(한달사용지출액) = 유저의 월예산(유저테이블) - 통장잔고 (account테이블)
				int monthIn = libMapper.monthIncome(userPK);
				dto.setMonthBalance(monthIn - userAcc.getBalance()); // dto에 한달사용지출액 넣기
				
			} else if(userAcc.getAccCk() == 1) { //적금계좌일때
				dto.setMonthBalance(0);
			} else { // 신파일러 출금계좌일때, 한달사용금액 = balance - limitAmount
				int Bala = ttfMapper.seeBalance(userAcc.getAccountId());
				int limitAmo = ttfMapper.seeLimitAmount(userAcc.getAccountId());
				dto.setMonthBalance(Bala - limitAmo);	
			}
			
			dtoList.add(dto);	
		}
		
		
	}
	
	

}
