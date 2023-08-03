package com.ant.ttf.domain.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ant.ttf.domain.library.dto.request.UpdateNicknameDTO;
import com.ant.ttf.domain.library.dto.response.LibTotalResDTO;
import com.ant.ttf.domain.library.mapper.LibMapper;
import com.ant.ttf.domain.library.service.LibService;
import com.ant.ttf.global.ResponseFormat;
import com.ant.ttf.global.ResponseStatus;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/v1/library")
@Log
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LibController {

	@Autowired
	LibService libService;
	
	@GetMapping("/totalacc") //라이브러리(입출금계좌, 적금) 전체 가져오는 API
	public ResponseEntity<ResponseFormat<List<LibTotalResDTO>>> totalAcc(@RequestHeader("X-AUTH-TOKEN") String token) throws Exception{
		List<LibTotalResDTO> dtoList = libService.seeTotalAcc(token);
		ResponseFormat<List<LibTotalResDTO>> responseFormat = new ResponseFormat<>(ResponseStatus.LIBRARY_GETALLACC_SUCCESS, dtoList);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}  
	
	// 계좌별 닉네임 변경 API
	@PutMapping("/nickname")
	public ResponseEntity<ResponseFormat<ResponseStatus>> updateNickname(@RequestHeader("X-AUTH-TOKEN") String token, @RequestBody UpdateNicknameDTO dto)throws Exception{
		libService.updateNickname(token, dto);
		ResponseFormat<ResponseStatus> responseFormat = new ResponseFormat<>(ResponseStatus.LIBRARY_UPDATE_SUCCESS);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}
	
}
