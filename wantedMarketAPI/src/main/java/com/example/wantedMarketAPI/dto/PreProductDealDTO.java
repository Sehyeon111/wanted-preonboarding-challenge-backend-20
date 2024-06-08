package com.example.wantedMarketAPI.dto;

import com.example.wantedMarketAPI.entity.PreProductDealEntity;

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

@Builder()
public class PreProductDealDTO {
	private Long dealSeq;
	private Long productSeq;
	private String productNow;
	private String productSeller;
	private String productBuyer;
	
	public static PreProductDealDTO toDTO(PreProductDealEntity entity, Long productSeq
			, String sellerName, String buyerName) {
		return PreProductDealDTO.builder()
				.dealSeq(entity.getDealSeq())
				.productSeq(productSeq)
				.productNow(entity.getProductNow())
				.productSeller(sellerName)
				.productBuyer(buyerName)
				.build();
	}
}
