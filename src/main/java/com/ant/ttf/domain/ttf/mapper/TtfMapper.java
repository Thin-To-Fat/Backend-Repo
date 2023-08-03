package com.ant.ttf.domain.ttf.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ant.ttf.domain.ttf.entity.Ttf;

@Mapper
public interface TtfMapper {
	
	String findAccountNum(Long accountId); //계좌번호 조회
	int regTtf(Ttf ttf); // 신파일러 적금가입
	
	int seeLimit(String userPK); //userPK를 이용한 신파일러 계좌 한도조회
	
	int balUpdate(Map map); // 신파일러 결제
	
	int seeBalance(Long account_id); //신파일러 계좌 잔액조회
	
	int seeLimitAmount(Long account_id); //userPK가 아닌 account_id를 이용한 한도조회. 유저는 한명인데 계좌는 여러개일수도 있으니..
	
	int updateBnpl(Map map); // 대금결제일 db에 삽입
}
