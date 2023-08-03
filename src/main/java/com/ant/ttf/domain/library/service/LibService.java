package com.ant.ttf.domain.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ant.ttf.domain.library.dto.request.UpdateNicknameDTO;
import com.ant.ttf.domain.library.dto.response.LibTotalResDTO;

@Service
public interface LibService {
	
	public List<LibTotalResDTO> seeTotalAcc(String token) throws Exception;
	public void updateNickname(String token, UpdateNicknameDTO dto) throws Exception;
}
