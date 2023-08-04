package com.ant.ttf.domain.users.service;


import org.springframework.stereotype.Service;

import com.ant.ttf.domain.users.dto.request.UsersLoginReqDTO;
import com.ant.ttf.domain.users.dto.request.UsersRequestDTO;
import com.ant.ttf.domain.users.dto.response.UserDashboardInfoDTO;
import com.ant.ttf.domain.users.dto.response.UsersLoginResponseDTO;
import com.ant.ttf.domain.users.entity.Users;

@Service
public interface UsersService {
	public UsersLoginResponseDTO login(UsersLoginReqDTO loginDto);
	public Users findByUserId(Long userId);
	public UserDashboardInfoDTO userDshInfo(String token);
	
}
