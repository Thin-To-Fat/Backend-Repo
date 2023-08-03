package com.ant.ttf.domain.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ant.ttf.domain.library.dto.request.UpdateNicknameDTO;
import com.ant.ttf.domain.library.entity.Account;
import com.ant.ttf.domain.library.entity.BankInfo;

@Mapper
public interface LibMapper {
	
//	Account seeAcc(String userPK);
	int monthIncome(String userPK); // 유저의 월예산 조회
	BankInfo nameImg(Long bankInfo);
	
	List<Account> listAcc(String userPK); //특정유저가 가지고 있는 계좌들을 전부 리스트로 가지고 온다.
	
	public void updateNickname(UpdateNicknameDTO dto); 
}
