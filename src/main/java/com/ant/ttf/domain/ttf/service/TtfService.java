package com.ant.ttf.domain.ttf.service;

import org.springframework.stereotype.Service;

import com.ant.ttf.domain.ttf.dto.request.TtfJoinReqDTO;

@Service
public interface TtfService {
	public void thinJoin(String token, TtfJoinReqDTO dto) throws Exception; // 신파일러 적금 가입
	public boolean ttfPay(String token, String productPrice) throws Exception; // 한도초과시 상품결제 막기
	public void dateOfPay(String token, String bnpl) throws Exception; // 대금납부일 받기
}
