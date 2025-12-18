package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RecommendationRecordEntity;
import com.example.demo.repository.RecommendationRecordRepository;

@Service
public class RecommendationRecordServiceImpl implements RecommendationRecordService{
    @Autowired
    RecommendationRecordRepository rr;
    @Override
    public Optional<RecommendationRecordEntity> getRecommendationById(Long id){
        return rr.findById(id);
    }
    public Optional<RecommendationRecordEntity> getRecommendationByUser(Long userid){
        return rr.findById(userid);
    }
    public List<RecommendationRecordEntity> getRecommendations(){
        return rr.findAll();
    }
}
