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
	ATTEND_PUT_POINTUP_SUCCESS("U006", "출석체크 성공"),
	GOTCHA_GET_USERTITLE_SUCCESS("U007", "칭호뽑기 성공"),
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
	HISTORY_GET_HISTORYINFO_SUCCESs("H009", "수입 / 지출 내역 상단 데이터 가져오기 성공"),
	
	// 신파일러 관련 API
	TTF_POSTSIGNUP_SUCCESS("T001", "신파일러 적금 가입하기 성공"),
	TTF_PUTPAY_SUCCESS("T002", "상품 결제 성공"),
	TTF_PUTPAYSTOP_SUCCESS("T003", "한도 초과 결제 실패"),
	TTF_PUTDATEOFPAY_SUCCESS("T004", "대금 납부일 받기 성공"),
	TTF_QRINFO_SUCCESS("T005", "QR 화면 정보 가져오기 성공"),
	TTF_REGISTCHECK_SUCCESS("T006", "ttf 적금 가능여부 확인 성공"),
	//라이브러리 관련 API
	LIBRARY_GETALLACC_SUCCESS("L001", "라이브러리 전체 가져오기 성공"),
	LIBRARY_UPDATE_SUCCESS("L002", "계좌 별 닉네임 수정 성공"),

	// 은행 리스트 API
	SAVINGS_GET_BANKINFO_SUCCESS("B001", "은행 리스트 가져오기 성공"),
	
	// 적금 상품 검색 조회 API
	SAVING_GET_LIST_SUCCESS("S001", "적금 상품 정보 가져오기 성공"),
	
	HISTORY_GET_FILTERCATEGORYELEMENT_SUCCESS("H007", "필터 카테고리 내용 가져오기 성공"),
	HISTORY_GET_FILTERACCOUNTELEMENT_SUCCESS("H008", "필터 계좌 내용 가져오기 성공"),
	HISTORY_GET_HISTORYHEADINFO_SUCCESS("H010", "최고지출일,일평균지출,월별최고지출액, 월별최고수입액, 개인포인트 데이터 가져오기 성공"),
	HISTORY_GET_HISTORYTAILINFO_SUCCESS("H011", "일일최고 수입, 일일최고 지출, 당월 일별 수입, 당월 일별 지출 데이터 가져오기 성공");

	// 위와 유사하게 적어주세요
	
	private final String code;
    private final String message;
}
