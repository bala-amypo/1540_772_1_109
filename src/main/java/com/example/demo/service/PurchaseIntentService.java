package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.PurchaseIntentRecordEntity;

@Service
public interface  PurchaseIntentService{
    PurchaseIntentRecordEntity createIntent(PurchaseIntentRecordEntity pe);
    Optional<PurchaseIntentRecordEntity> getIntentByUser(Long userId);
    Optional<PurchaseIntentRecordEntity> getIntentById(Long id);
    List<PurchaseIntentRecordEntity> getAllIntents();
}
