package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PurchaseIntentRecordEntity;

public interface PurchaseIntentRecordRepository extends JpaRepository<PurchaseIntentRecordEntity,Long>{
    
}
