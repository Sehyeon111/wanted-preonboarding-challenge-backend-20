package com.example.wantedMarketAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wantedMarketAPI.entity.PreProductEntity;

public interface PreProductRepository extends JpaRepository<PreProductEntity, Long> {

}
