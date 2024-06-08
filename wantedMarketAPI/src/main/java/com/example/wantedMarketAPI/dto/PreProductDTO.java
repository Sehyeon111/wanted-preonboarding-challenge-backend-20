package com.example.wantedMarketAPI.dto;

import com.example.wantedMarketAPI.entity.PreProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Builder
public class PreProductDTO {
	private Long productSeq;
	private String productName;
	private int productPrice;
	private String productSeller;
	private String productNow;
	
	public static PreProductDTO toDTO(PreProductEntity preProductEntity) {
		return PreProductDTO.builder()
			.productSeq(preProductEntity.getProductSeq())
			.productName(preProductEntity.getProductName())
			.productPrice(preProductEntity.getProductPrice())
			.productSeller(preProductEntity.getProductSeller())
			.productNow(preProductEntity.getProductNow())
			.build();
		
	}
	
}
