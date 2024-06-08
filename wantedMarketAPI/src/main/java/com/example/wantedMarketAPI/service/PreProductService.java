package com.example.wantedMarketAPI.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.wantedMarketAPI.dto.PreProductDTO;
import com.example.wantedMarketAPI.entity.PreProductEntity;
import com.example.wantedMarketAPI.repository.PreProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PreProductService {
	private final PreProductRepository preProductRepository;
	
	// 상세조회할 물품 정보 가져오기
	public PreProductDTO findOneDetail(Long productSeq) {
		PreProductEntity entity = preProductRepository.findById(productSeq).get();
		
		return PreProductDTO.toDTO(entity);
	}

	// 모든 물품 리스트 가져오기
	public List<PreProductDTO> allProduct() {
		List<PreProductEntity> productEntityList = preProductRepository.findAll();
		List<PreProductDTO> productDTOList = productEntityList.stream()
				.map(entity -> PreProductDTO.toDTO(entity))
				.collect(Collectors.toList());
		
		return productDTOList;
		}

	public PreProductEntity deal(Long productSeq) {
		PreProductEntity entity = preProductRepository.findById(productSeq).get();
		entity.setProductName("완료");
		return entity;
	}
		
	}

