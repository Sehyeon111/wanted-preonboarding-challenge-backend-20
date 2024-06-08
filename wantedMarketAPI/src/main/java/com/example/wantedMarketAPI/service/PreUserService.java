package com.example.wantedMarketAPI.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.wantedMarketAPI.dto.PreUserDTO;
import com.example.wantedMarketAPI.entity.PreUserEntity;
import com.example.wantedMarketAPI.repository.PreUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PreUserService {

	public final PreUserRepository preUserRepository;
	public final BCryptPasswordEncoder bCryptPasswordEncoder;
//	public final PreUserEntity preUserEntity;
	// 회원가입
	public boolean insertUser(PreUserDTO preUserDTO) {
		Optional<PreUserEntity> isExistUser = preUserRepository.findById(preUserDTO.getUserId());
		if(isExistUser.get()!=null) {
			return false;
		}
		
		// 비번 암호화
		preUserDTO.setUserPw(bCryptPasswordEncoder.encode(preUserDTO.getUserPw()));
		preUserRepository.save(PreUserEntity.toEntity(preUserDTO));
		return true;
	}
	public PreUserEntity findById(String username) {
		PreUserEntity entity = preUserRepository.findById(username).get();
		return entity;
	}

}
