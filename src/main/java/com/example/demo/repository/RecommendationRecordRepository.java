package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.RecommendationRecordEntity;

public interface RecommendationRecordRepository extends JpaRepository<RecommendationRecordEntity,Long>{
    
}
