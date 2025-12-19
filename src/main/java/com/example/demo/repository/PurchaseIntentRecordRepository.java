package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PurchaseIntentRecord;

public interface PurchaseIntentRecordRepository extends JpaRepository<PurchaseIntentRecord,Long>{
    
}
