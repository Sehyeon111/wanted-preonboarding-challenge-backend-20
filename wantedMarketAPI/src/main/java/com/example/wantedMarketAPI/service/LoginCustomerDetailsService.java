package com.example.wantedMarketAPI.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.wantedMarketAPI.dto.LoginUserDetails;
import com.example.wantedMarketAPI.dto.PreUserDTO;
import com.example.wantedMarketAPI.entity.PreUserEntity;
import com.example.wantedMarketAPI.repository.PreUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginCustomerDetailsService implements UserDetailsService {

	private static PreUserRepository preUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		PreUserEntity preUserEntity = preUserRepository.findById(userId)
				.orElseThrow(() -> {
					throw new UsernameNotFoundException("err 발생");
				});
		
		return new LoginUserDetails(PreUserDTO.toDTO(preUserEntity));
	}

}
