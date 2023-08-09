package com.ant.ttf.domain.savings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ant.ttf.domain.savings.mapper.SavingsMapper;
import com.ant.ttf.domain.savings.service.SavingsService;
import com.ant.ttf.global.ResponseFormat;
import com.ant.ttf.global.ResponseStatus;
import com.ant.ttf.global.jwt.service.JwtTokenProvider;
import java.util.Map;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/v1/savings")
@CrossOrigin(origins = "http://localhost:3000/")
@Log
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SavingsController {
	
	@Autowired
	SavingsService savingsService;
	
	@Autowired
	SavingsMapper savingsMapper;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;

	// 은행 리스트 불러오는 API
	@GetMapping("/bankInfo")
	public ResponseEntity<ResponseFormat<List<Map>>> bankinfo() throws Exception{
		List<Map> bankEle = savingsMapper.findBankInfo();
		ResponseFormat<List<Map>> responseFormat = new ResponseFormat<>(ResponseStatus.SAVINGS_GET_BANKINFO_SUCCESS, bankEle);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
	
	// 적금 상품리스트 조회 API
	@GetMapping("/list")
	public ResponseEntity<ResponseFormat<List<Map>>> getSavingsList(@RequestParam Map param) throws Exception{
		List<Map> list = savingsMapper.findSavingsList(param);
		ResponseFormat<List<Map>> responseFormat = new ResponseFormat<>(ResponseStatus.SAVING_GET_LIST_SUCCESS, list);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
}
