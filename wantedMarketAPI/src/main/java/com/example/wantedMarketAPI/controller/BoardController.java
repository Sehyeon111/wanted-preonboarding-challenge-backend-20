package com.example.wantedMarketAPI.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.wantedMarketAPI.dto.PreProductDTO;
import com.example.wantedMarketAPI.dto.PreProductDealDTO;
import com.example.wantedMarketAPI.entity.PreProductDealEntity;
import com.example.wantedMarketAPI.entity.PreProductEntity;
import com.example.wantedMarketAPI.entity.PreUserEntity;
import com.example.wantedMarketAPI.service.PreProductDealService;
import com.example.wantedMarketAPI.service.PreProductService;
import com.example.wantedMarketAPI.service.PreUserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final PreProductService	preProductService;
	private final PreProductDealService preProductDealService;
	private final PreUserService preUserService;
	
	// 물품 목록 조회 API
	@GetMapping("/list")
	public String boardList(Model model) {
		List<PreProductDTO> productList = preProductService.allProduct();
		
		model.addAttribute("productList", productList);
		return "boardList";
	}
	
	// 물품 상세 정보 조회 API
	@GetMapping("/detailList")
	public String detailList(@ModelAttribute Long productSeq
			, Model model){
		// 로그인한 유저 정보 가져오기
		String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		// 물품 상세 정보 가져오기
		PreProductDTO preProductDTO = preProductService.findOneDetail(productSeq); 
		
		// 예약상태가 예약중이거나 완료이고
		if(preProductDTO.getProductNow()!="판매중") {
			PreProductDealDTO preProductDealDTO = preProductDealService.findOneDeal(PreProductEntity.toEntity(preProductDTO));
			// 로그인한 유저가 해당 물품의 거래 당사자이면
			if(username==preProductDealDTO.getProductBuyer() || username==preProductDealDTO.getProductSeller()) {
				// 거래 현황도 전달
				model.addAttribute("preProductDealDTO",preProductDealDTO);
			}
		}
		model.addAttribute("preProductDTO",preProductDTO);
		return "detailList";
	}
	
	// 내가 구매한 & 예약한 물품 조회 API
	@GetMapping("/myDealList")
	public String myDealList(Model model) {
		// 로그인한 유저 id 가져오기
		String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		// 내가 구매한 물품의 상세 list 가져오기;
		List<PreProductDealEntity> completeDealEntity = preProductDealService.findCompleteList(username);
		// 물품 DTO만 담을 배열 선언
		List<PreProductDTO> completeProductList = new ArrayList<>();
		// 물품 상세에 해당하는 물품 DTO 배열에 저장
		completeDealEntity.forEach(temp -> completeProductList.add(PreProductDTO.toDTO(temp.getPreProductEntity())));
		
		// 예약중 & 내가 거래 당사자인 물품의 상세 list 가져오기
		List<PreProductDealEntity> ingDealEntity = preProductDealService.findIngList(username);
		// 본인이 어떤 거래 당사자인지와 물품의 상세를 담을 map 선언
		Map<String, PreProductDTO> ingDealMap= new HashMap<>();
		// Mapping (내가 판매자인 경우와 구매자인 경우 if문으로 구별)
		ingDealEntity.forEach(temp -> {if(temp.getProductSeller().getUserId()==username) {
			ingDealMap.put("판매자", PreProductDTO.toDTO(temp.getPreProductEntity()));}
		else {
			ingDealMap.put("구매자", PreProductDTO.toDTO(temp.getPreProductEntity()));
		}
		});
		
		model.addAttribute("completeProductList",completeProductList);
		model.addAttribute("ingDealMap",ingDealMap);
		return "myDealList";
	}
	
	// 판매승인
	@GetMapping("/deal")
	public void deal(@RequestParam(name="productSeq") Long productSeq
			, @RequestParam(name="productBuyer") String buyer) {
		// 사용자이자 판매자 id
		String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		PreProductEntity entity = preProductService.deal(productSeq);
		PreUserEntity productSeller = preUserService.findById(username);
		PreUserEntity productBuyer = preUserService.findById(buyer);
		preProductDealService.deal(productSeller, productBuyer, entity);
	}
	
}
