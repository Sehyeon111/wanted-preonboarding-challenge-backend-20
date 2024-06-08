package com.example.wantedMarketAPI.dto;

import com.example.wantedMarketAPI.entity.PreUserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter

@Builder
public class PreUserDTO {

	private String userId;

	private String userPw;
	
	private String userRole;
	
	public static PreUserDTO toDTO(PreUserEntity preuserEntity) {
		return PreUserDTO.builder()
				.userId(preuserEntity.getUserId())
				.userPw(preuserEntity.getUserPw())
				.userRole(preuserEntity.getUserRole())
				.build();
	};
}
