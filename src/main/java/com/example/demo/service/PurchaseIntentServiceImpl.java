package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PurchaseIntentRecordEntity;
import com.example.demo.repository.PurchaseIntentRecordRepository;

@Service
public class PurchaseIntentServiceImpl implements PurchaseIntentRecordService{
    @Autowired
    PurchaseIntentRecordRepository pr;
    @Override
    public PurchaseIntentRecordEntity createIntent(PurchaseIntentRecordEntity pe){
        return pr.save(pe);
    }
    @Override
    public Optional<PurchaseIntentRecordEntity> getIntentByUser(Long userId){
        return pr.findById(userId);
    }
    @Override
    public Optional<PurchaseIntentRecordEntity> getIntentById(Long id){
        return pr.findById(id);
    }
    @Override
    public List<PurchaseIntentRecordEntity> getAllIntents(){
        return pr.findAll();
    }
}
