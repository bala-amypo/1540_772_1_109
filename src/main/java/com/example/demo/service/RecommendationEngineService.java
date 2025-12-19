package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.RecommendationRecord;

@Service
public interface RecommendationEngineService {
    Optional<RecommendationRecord> getRecommendationById(Long id);
    Optional<RecommendationRecord> getRecommendationByUser(Long userid);
    List<RecommendationRecord> getRecommendations();
}
