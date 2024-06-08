package com.example.wantedMarketAPI.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUserDetails implements UserDetails {

	private String userId;
	private String userPw;
	private String userRole;
	private static final long serialVersionUID = 1L;
	
	public LoginUserDetails(PreUserDTO preUserDTO) {
		super();
		this.userId = preUserDTO.getUserId();
		this.userPw = preUserDTO.getUserPw();
		this.userRole = preUserDTO.getUserRole();
	}
	
	// 계정이 갖고있는 권한들의 목록을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(() -> {return this.userRole;});
//			new GrantedAuthority() {
//				private static final long serialVersionUID = 1L;
//				
//				@Override
//				public String getAuthority() {
//					return userRole;
//				}}
				
//			);
		return collection;
	}

	@Override
	public String getPassword() {
		return this.userPw;
	}

	@Override
	public String getUsername() {
		return this.userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
