package com.example.wantedMarketAPI.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.wantedMarketAPI.dto.PreProductDealDTO;
import com.example.wantedMarketAPI.entity.PreProductDealEntity;
import com.example.wantedMarketAPI.entity.PreProductEntity;
import com.example.wantedMarketAPI.entity.PreUserEntity;
import com.example.wantedMarketAPI.repository.PreProductDealRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PreProductDealService {
	private final PreProductDealRepository preProductDealRepository;
	
	// 물품 상세 정보 가져오기
	public PreProductDealDTO findOneDeal(PreProductEntity preProductEntity) {
		PreProductDealEntity entity = preProductDealRepository.findByPreProductEntity(preProductEntity);
		return PreProductDealDTO.toDTO(entity, entity.getPreProductEntity().getProductSeq()
				, entity.getProductSeller().getUserId(), entity.getProductSeller().getUserId());
	}

	
	// 내가 구매한 물품 상세 정보 가져오기
	public List<PreProductDealEntity> findCompleteList(String username) {
		List<PreProductDealEntity> completeDealEntity = preProductDealRepository.findCompleteList(username);
		return completeDealEntity;
	}
	
	// 내가 거래 당사자이고, 예약중인 정보 가져오기
	public List<PreProductDealEntity> findIngList(String username) {
		List<PreProductDealEntity> ingEntity = preProductDealRepository.findIngList(username);
		return ingEntity;
	}


	public void deal(PreUserEntity productSeller, PreUserEntity productBuyer, PreProductEntity entity) {
		PreProductDealEntity entity2 = preProductDealRepository.findByPreProductEntity(entity);
		entity2.setProductNow("완료");
		entity2.setProductSeller(productSeller);
		entity2.setProductBuyer(productBuyer);
		
	}



}
