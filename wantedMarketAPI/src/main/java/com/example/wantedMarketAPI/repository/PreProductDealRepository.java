package com.example.wantedMarketAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.wantedMarketAPI.entity.PreProductDealEntity;
import com.example.wantedMarketAPI.entity.PreProductEntity;

public interface PreProductDealRepository extends JpaRepository<PreProductDealEntity, Long> {

	PreProductDealEntity findByPreProductEntity(PreProductEntity entity);

	@Query(value = "SELECT * FROM preProductDealEntity WHERE productNow = '완료' AND productSeller = :username", nativeQuery = true)
	List<PreProductDealEntity> findCompleteList(String username);
	
	@Query(value = "SELECT * FROM preProductDealEntity WHERE (productNow = '예약중' AND productSeller = :username) "
			+ "OR (productNow = '예약중' AND productBuyer = :username)", nativeQuery = true)
	List<PreProductDealEntity> findIngList(String username);
}
