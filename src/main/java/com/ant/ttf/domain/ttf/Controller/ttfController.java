package com.ant.ttf.domain.ttf.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ant.ttf.domain.ttf.dto.request.TtfJoinReqDTO;
import com.ant.ttf.domain.ttf.service.TtfService;
import com.ant.ttf.global.ResponseFormat;
import com.ant.ttf.global.ResponseStatus;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/v1/ttf")
@Log
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ttfController {
	
	@Autowired
	TtfService ttfService;
	
	@PostMapping("/join") //신파일러 적금가입 API
	public ResponseEntity<ResponseFormat<ResponseStatus>> ttfJoin(@RequestHeader("X-AUTH-TOKEN") String token, @RequestBody TtfJoinReqDTO dto) throws Exception{
		ttfService.thinJoin(token, dto);
		ResponseFormat<ResponseStatus> responseFormat = new ResponseFormat<>(ResponseStatus.TTF_POSTSIGNUP_SUCCESS);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
	}  
	
	@PutMapping("/payrefusal") //신파일러 결제 API
	public ResponseEntity<ResponseFormat<ResponseStatus>> payProduct(@RequestHeader("X-AUTH-TOKEN") String token, @RequestParam("productPrice") String productPrice) throws Exception{
		boolean mention = ttfService.ttfPay(token, productPrice);
		if (mention == false) {
			ResponseFormat<ResponseStatus> responseFormat = new ResponseFormat<>(ResponseStatus.TTF_PUTPAYSTOP_SUCCESS);
			return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
		} else {
			ResponseFormat<ResponseStatus> responseFormat = new ResponseFormat<>(ResponseStatus.TTF_PUTPAY_SUCCESS);
			return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
		}
		
	}
	

}
