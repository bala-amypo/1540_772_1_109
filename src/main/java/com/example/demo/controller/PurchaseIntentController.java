package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PurchaseIntentRecordEntity;
import com.example.demo.service.PurchaseIntentRecordService;

@RestController
public class PurchaseIntentController {
    @Autowired
    PurchaseIntentRecordService ps;
    @PostMapping("/rewardrule")
    public PurchaseIntentRecordEntity create(@RequestBody PurchaseIntentRecordEntity pe){
        return ps.createIntent(pe);
    }
    @GetMapping("/user/{userId}")
    public Optional<PurchaseIntentRecordEntity> get(@PathVariable Long re){
        return ps.getIntentByUser(re);
    }
    @GetMapping("/Id/{id}")
    public Optional<PurchaseIntentRecordEntity> getid(@PathVariable Long re){
        return ps.getIntentById(re);
    }
    @GetMapping("/all")
    public List<PurchaseIntentRecordEntity> getall(){
        return ps.getAllIntents();
    }
}
