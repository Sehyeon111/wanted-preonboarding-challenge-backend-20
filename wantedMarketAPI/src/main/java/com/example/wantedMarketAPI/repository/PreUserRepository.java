package com.example.wantedMarketAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wantedMarketAPI.entity.PreProductDealEntity;
import com.example.wantedMarketAPI.entity.PreUserEntity;

public interface PreUserRepository extends JpaRepository<PreUserEntity, String> {

}
