package com.example.wantedMarketAPI.entity;

import com.example.wantedMarketAPI.dto.PreProductDealDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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

@Entity
@Builder
@Table(name="preproductdeal")
public class PreProductDealEntity {
	@SequenceGenerator(name="dealSeq"
			, sequenceName="dealSeq"
			, initialValue = 1
			, allocationSize = 1)
	
	@Id
	@Column(name="deal_seq")
	@GeneratedValue(generator="dealSeq")
	private Long dealSeq;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_seq")
	private PreProductEntity preProductEntity;
	
	@Column(name="product_now")
	private String productNow;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_seller")
	private PreUserEntity productSeller;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_buyer")
	private PreUserEntity productBuyer;
	
	public static PreProductDealEntity toEntity(PreProductDealDTO dealDTO, PreProductEntity productEntity
			, PreUserEntity productSeller, PreUserEntity productBuyer) {
		return PreProductDealEntity.builder()
				.dealSeq(dealDTO.getDealSeq())
				.preProductEntity(productEntity)
				.productNow(dealDTO.getProductNow())
				.productSeller(productSeller)
				.productBuyer(productBuyer)
				.build();
	}
}
