package com.ant.ttf.global;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {
	
	USER_POSTSIGNUP_SUCCESS("U000", "회원가입 성공"),
	USER_POSTLOGIN_SUCCESS("U001", "로그인 성공, 토큰을 발급합니다."),
	
	// 유저 정보 관련 API
	DASHBOARD_GET_USERINFO_SUCCESS("U002", "대시보드 상단 유저 정보 가져오기 성공"),
	DASHBOARD_PUT_USERINCOME_SUCCESS("U003", "유저 월 예산 수정 성공"),
	DASHBOARD_PUT_USERBUDGET_SUCCESS("U004", "유저 월 목표금액 수정 성공"),
	
	// 지출내역 관련 API
	DASHBOARD_GET_HISTORYFIXED_SUCCESS("H001", "예상 고정 지출 금액 받아오기 성공"),
	DASHBOARD_GET_HISTORYCATEGORY_SUCCESS("H002", "이번달 카테고리별 지출금 받아오기 성공"),
	DASHBOARD_GET_HISTORYTODAY_SUCCESS("H003", "현재 하루 지출 상태 가져오기 성공"),
	DASHBOARD_GET_HISTORYTHREEMONTH_SUCCESS("H004", "3개월 기준 지출 정보 받아오기 성공"),
	DASHBOARD_GET_HISTORYTOP3_SUCCESS("H005", "지출 탑3와 예산소지율 가져오기 성공"),
	DASHBOARD_GET_HISTORYMONCHANGE_SUCCESS("H006", "달별 올해 지출 가져오기 성공"),
	
	// 수입/지출 내역
	HISTORY_GET_FILTERELEMENT_SUCCESS("H007", "카테고리, 계좌 필터 요소 가져오기 성공"),
	HISTORY_GET_FILTERDATA_SUCCESS("H008", "달, 카테고리, 은행 별 지출내역 가져오기 성공"),
	HISTORY_GET_HISTORYINFO_SUCCESs("H009", "수입 / 지출 내역 상단 데이터 가져오기 성공");
	
	
	// 위와 유사하게 적어주세요
	
	private final String code;
    private final String message;
}
