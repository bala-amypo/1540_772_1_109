package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.repository.PurchaseIntentRecordRepository;

@Service
public class PurchaseIntentServiceImpl implements PurchaseIntentService{
    @Autowired
    PurchaseIntentRecordRepository pr;
    @Override
    public PurchaseIntentRecord createIntent(PurchaseIntentRecord pe){
        return pr.save(pe);
    }
    @Override
    public Optional<PurchaseIntentRecord> getIntentByUser(Long userId){
        return pr.findById(userId);
    }
    @Override
    public Optional<PurchaseIntentRecord> getIntentById(Long id){
        return pr.findById(id);
    }
    @Override
    public List<PurchaseIntentRecord> getAllIntents(){
        return pr.findAll();
    }
}
