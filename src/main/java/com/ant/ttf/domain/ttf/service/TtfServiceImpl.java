package com.ant.ttf.domain.ttf.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ant.ttf.domain.library.entity.Account;
import com.ant.ttf.domain.ttf.dto.request.TtfJoinReqDTO;
import com.ant.ttf.domain.ttf.entity.Ttf;
import com.ant.ttf.domain.ttf.mapper.TtfMapper;
import com.ant.ttf.global.exception.BnplLengthException;
import com.ant.ttf.global.jwt.service.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TtfServiceImpl implements TtfService {
	
	
	private final JwtTokenProvider jwtTokenProvider;
	private final TtfMapper ttfMapper;
	
	
	@Override
	public void thinJoin(String token, TtfJoinReqDTO dto) throws Exception { // 신파일러 적금 계좌 가입
		String userPK = jwtTokenProvider.getUserPk(token);
		//여기에 account_num(ttf테이블) 생성 => 참고로 account_num(ttf자체계좌번호) = acc_num(Account 테이블) 이다. 
		//ttf테이블의 account_id는 ttf계좌로 매달 돈이 입금될 때, 이 돈의 출금계좌이다.
		//계좌생성
		String newAccNo = "";
		int cnt = 0;
		while(cnt < 14) {
			int digit = (int)(Math.random()*10);
			newAccNo += digit;
			cnt++;
		}
		StringBuilder sb = new StringBuilder(newAccNo);
		sb.setCharAt(3, '-');
		sb.setCharAt(7, '-');
		newAccNo = sb.toString();
		
		Long userPK2 = Long.parseLong(userPK);
		Ttf ttf = dto.ttfForEntity(dto, userPK2, newAccNo);
		ttf.setLimitAmount(ttf.getBalance());
		int success = ttfMapper.regTtf(ttf);
		
		//새로운 account 객체 생성해서 db에 넣어주기
		Account thinAcc = new Account();
		thinAcc.setUserId(userPK2);
		thinAcc.setBankInfo(11l); //신한은행
		thinAcc.setNickname("추후 입력");
		thinAcc.setType("신파일러적금계좌");
		thinAcc.setCreatedAt(LocalDateTime.now());
		thinAcc.setAccNum(newAccNo);
		thinAcc.setBalance(ttf.getBalance());
		thinAcc.setAccCk(1);
		
		int success2 = ttfMapper.regAcc(thinAcc);
		
		log.info("신파일러 가입 성공" + success2);
	}
	
	@Override
	public boolean ttfPay(String token, String productPrice) throws Exception { // 한도초과시 상품결제 막기
		String userPK = jwtTokenProvider.getUserPk(token);
		int limitBalance = ttfMapper.seeLimit(userPK);
		int productPrice2 = Integer.parseInt(productPrice);
		
		if(limitBalance < productPrice2) {
			log.info("사용가능한도 초과");
			return false;
		} else { //사용가능 한도가 상품가격보다 크면 결제
			limitBalance = limitBalance - productPrice2;
			Map map = new HashMap();
			map.put("userPK", userPK);
			map.put("limitBalance", limitBalance);
			
			int success = ttfMapper.balUpdate(map);
			log.info("결제가 성공이면 1 : " + success);
			
			return true;
		}
	}
	
	@Override
	public void dateOfPay(String token, String bnpl) throws Exception { // 앱 화면에서 대금납부일 받기
		String userPK = jwtTokenProvider.getUserPk(token);
		int bnplNo = Integer.parseInt(bnpl);
		
		if(bnplNo <= 30) {
			Map map = new HashMap();
			map.put("userPK", userPK);
			map.put("bnpl", bnpl);
			int success = ttfMapper.updateBnpl(map);
			log.info("성공? :"+ (Integer.toString(success)));
		} else { // 대금결제일이 30을 넘어갈때!
			throw new BnplLengthException("올바르지 않은 입력입니다");
		}
		
	}
}
