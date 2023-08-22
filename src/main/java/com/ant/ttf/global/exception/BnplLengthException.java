package com.ant.ttf.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class BnplLengthException extends RuntimeException {
	
	public BnplLengthException(String message) {
		super(message);
	}
}
