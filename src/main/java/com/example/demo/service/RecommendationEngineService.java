package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.RecommendationRecordEntity;

@Service
public interface RecommendationEngineService {
    Optional<RecommendationRecordEntity> getRecommendationById(Long id);
    Optional<RecommendationRecordEntity> getRecommendationByUser(Long userid);
    List<RecommendationRecordEntity> getRecommendations();
}
