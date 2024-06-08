package com.example.wantedMarketAPI.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.wantedMarketAPI.dto.PreProductDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
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
@Table(name="preproduct")
public class PreProductEntity {

	@SequenceGenerator(
			name="preproductSeq"
			, sequenceName="preproductSeq"
			, initialValue = 1
			, allocationSize = 1
			)
	
	@Id
	@GeneratedValue(generator = "preproductSeq")
	@Column(name="product_seq")
	private Long productSeq;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_price")
	private int productPrice;
	
	@Column(name="product_seller")
	private String productSeller;
	
	@Column(name="product_now")
	private String productNow;
	

	@OneToMany(mappedBy = "preProductEntity"
			, cascade = CascadeType.REMOVE
			, orphanRemoval = true
			, fetch = FetchType.LAZY)
	@OrderBy("product_seq asc")
	private List<PreProductDealEntity> preProductDealEntity = new ArrayList<>();
	
	public static PreProductEntity toEntity(PreProductDTO preProductDTO) {
		return PreProductEntity.builder()
			.productSeq(preProductDTO.getProductSeq())
			.productName(preProductDTO.getProductName())
			.productPrice(preProductDTO.getProductPrice())
			.productSeller(preProductDTO.getProductSeller())
			.productNow(preProductDTO.getProductNow())
			.build();
	}
}
