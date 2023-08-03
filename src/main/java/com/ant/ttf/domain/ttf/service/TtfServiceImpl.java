package com.ant.ttf.domain.ttf.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ant.ttf.domain.ttf.dto.request.TtfJoinReqDTO;
import com.ant.ttf.domain.ttf.entity.Ttf;
import com.ant.ttf.domain.ttf.mapper.TtfMapper;
import com.ant.ttf.domain.users.mapper.UsersMapper;
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
//		Long accountId = ttfMapper.findAccountId(userPK); // 프론트단에서 account_id를 받는 요청이 따로 없어서, mapper에 있는 account테이블 이용해서 가져옴
		String accNum = ttfMapper.findAccountNum(dto.getAccountId());
		Long userPK2 = Long.parseLong(userPK);
		
		Ttf ttf = dto.ttfForEntity(dto, userPK2, accNum);
		int success = ttfMapper.regTtf(ttf);
		log.info("신파일러 가입 성공" + success);
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
		Map map = new HashMap();
		map.put("userPK", userPK);
		map.put("bnpl", bnpl);
		int success = ttfMapper.updateBnpl(map);
		log.info("성공? :"+ (Integer.toString(success)));
		
	}
}
