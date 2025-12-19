package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.PurchaseIntentRecord;

@Service
public interface  PurchaseIntentService{
    PurchaseIntentRecord createIntent(PurchaseIntentRecord pe);
    Optional<PurchaseIntentRecord> getIntentByUser(Long userId);
    Optional<PurchaseIntentRecord> getIntentById(Long id);
    List<PurchaseIntentRecord> getAllIntents();
}
