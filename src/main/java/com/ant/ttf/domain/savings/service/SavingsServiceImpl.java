package com.ant.ttf.domain.savings.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ant.ttf.domain.savings.mapper.SavingsMapper;
import com.ant.ttf.domain.users.mapper.UsersMapper;
import com.ant.ttf.global.jwt.service.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;


@Service
@RequiredArgsConstructor
@Log
public class SavingsServiceImpl implements SavingsService {
	
	private final SavingsMapper savingsMapper;
    private final UsersMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;


}
