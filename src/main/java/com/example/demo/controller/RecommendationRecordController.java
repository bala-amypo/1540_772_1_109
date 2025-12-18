package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.RecommendationRecordEntity;
import com.example.demo.service.RecommendationRecordService;

@RestController
public class RecommendationRecordController {
    @Autowired
    RecommendationRecordService rs;
    @GetMapping("/user/{userId}")
    public Optional<RecommendationRecordEntity> getid(@PathVariable Long re){
        return rs.getRecommendationById(re);
    }
    @GetMapping("/get/{id}")
    public Optional<RecommendationRecordEntity> getidd(@PathVariable Long re){
        return rs.getRecommendationByUser(re);
    }
    @GetMapping("/listall")
    public List<RecommendationRecordEntity> getiddd(){
        return rs.getRecommendations();
    }
}
