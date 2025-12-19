package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.RecommendationRecord;
import com.example.demo.service.RecommendationEngineService;

@RestController
public class RecommendationController {
    @Autowired
    RecommendationEngineService rs;
    @GetMapping("/usern/{userId}")
    public Optional<RecommendationRecord> getid(@PathVariable Long re){
        return rs.getRecommendationById(re);
    }
    @GetMapping("/get/{id}")
    public Optional<RecommendationRecord> getidd(@PathVariable Long re){
        return rs.getRecommendationByUser(re);
    }
    @GetMapping("/listall")
    public List<RecommendationRecord> getiddd(){
        return rs.getRecommendations();
    }
}
