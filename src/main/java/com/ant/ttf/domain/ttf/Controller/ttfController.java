package com.ant.ttf.domain.ttf.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ant.ttf.domain.ttf.dto.request.TtfJoinReqDTO;
import com.ant.ttf.domain.ttf.mapper.TtfMapper;
import com.ant.ttf.domain.ttf.service.TtfService;
import com.ant.ttf.global.ResponseFormat;
import com.ant.ttf.global.ResponseStatus;
import com.ant.ttf.global.jwt.service.JwtTokenProvider;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/v1/ttf")
@Log
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ttfController {
	
	@Autowired
	TtfService ttfService;
	
	@Autowired
	TtfMapper ttfMapper;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/join") //신파일러 적금가입 API
	public ResponseEntity<ResponseFormat<ResponseStatus>> ttfJoin(@RequestHeader("X-AUTH-TOKEN") String token, @RequestBody TtfJoinReqDTO dto) throws Exception{
		ttfService.thinJoin(token, dto);
		ResponseFormat<ResponseStatus> responseFormat = new ResponseFormat<>(ResponseStatus.TTF_POSTSIGNUP_SUCCESS);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
	}  
	
	@PutMapping("/payrefusal") //신파일러 결제 API
	public ResponseEntity<ResponseFormat<ResponseStatus>> payProduct(@RequestHeader("X-AUTH-TOKEN") String token, @RequestBody String productPrice) throws Exception{
		System.out.println(productPrice);
		boolean mention = ttfService.ttfPay(token, productPrice);
		if (mention == false) {
			ResponseFormat<ResponseStatus> responseFormat = new ResponseFormat<>(ResponseStatus.TTF_PUTPAYSTOP_SUCCESS);
			return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
		} else {
			ResponseFormat<ResponseStatus> responseFormat = new ResponseFormat<>(ResponseStatus.TTF_PUTPAY_SUCCESS);
			return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
		}
		
	}
	
	@PutMapping("/dateofpay") //대금결제일 받는 API
	public ResponseEntity<ResponseFormat<ResponseStatus>> ttfJoin(@RequestHeader("X-AUTH-TOKEN") String token, @RequestBody String bnpl) throws Exception{
		bnpl = bnpl.replace("\"", "");
		ttfService.dateOfPay(token, bnpl);
		ResponseFormat<ResponseStatus> responseFormat = new ResponseFormat<>(ResponseStatus.TTF_PUTDATEOFPAY_SUCCESS);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
	} 
	
	@GetMapping("/qrInfo")
	public ResponseEntity<ResponseFormat<Map>> qrInfo(@RequestHeader("X-AUTH-TOKEN") String token) throws Exception{
		String userPk = jwtTokenProvider.getUserPk(token);
		Map result = ttfMapper.qrInfo(userPk);
		ResponseFormat<Map> responseFormat = new ResponseFormat<>(ResponseStatus.TTF_QRINFO_SUCCESS, result);
		return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
	}

}
