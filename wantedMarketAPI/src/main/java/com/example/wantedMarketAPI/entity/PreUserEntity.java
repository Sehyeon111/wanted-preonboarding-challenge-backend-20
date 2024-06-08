package com.example.wantedMarketAPI.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.wantedMarketAPI.dto.PreUserDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
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
// userEntity 생성
@Builder
@Entity
@Table(name="preuser")
public class PreUserEntity {
	
	@Id
	@Column(name="user_id")
	private String userId;
	@Column(name="user_pw")
	private String userPw;
	@Column(name="user_role")
	private String userRole;
	
	@OneToMany(mappedBy = "productSeller"
			, cascade = CascadeType.REMOVE
			, orphanRemoval = true
			, fetch = FetchType.LAZY
			)
	@OrderBy("product_seq asc")
	private List<PreProductDealEntity> sellerPreProductDealEntity = new ArrayList<>();
	
	@OneToMany(mappedBy = "productBuyer"
			, cascade = CascadeType.REMOVE
			, orphanRemoval = true
			, fetch = FetchType.LAZY
			)
	@OrderBy("product_seq asc")
	private List<PreProductDealEntity> buyerPreProductDealEntity = new ArrayList<>();
	
	public static PreUserEntity toEntity(PreUserDTO preuserDTO) {
		return PreUserEntity.builder()
				.userId(preuserDTO.getUserId())
				.userPw(preuserDTO.getUserPw())
				.userRole(preuserDTO.getUserRole())
				.build();
	};
};


