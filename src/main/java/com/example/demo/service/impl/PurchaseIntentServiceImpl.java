package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PurchaseIntentRecordRepository;
import com.example.demo.service.PurchaseIntentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseIntentServiceImpl implements PurchaseIntentService {

    @Autowired
    private PurchaseIntentRecordRepository purchaseIntentRecordRepository;

    @Override
    public PurchaseIntentRecord createIntent(PurchaseIntentRecord intent) {

        if (intent.getAmount() == null || intent.getAmount() <= 0) {
            throw new BadRequestException("Amount must be greater than zero");
        }

        if (intent.getUserId() == null) {
            throw new BadRequestException("UserId is required");
        }

        return purchaseIntentRecordRepository.save(intent);
    }

    @Override
    public List<PurchaseIntentRecord> getIntentsByUser(Long userId) {
        return purchaseIntentRecordRepository.findByUserId(userId);
    }

    @Override
    public PurchaseIntentRecord getIntentById(Long id) {
        return purchaseIntentRecordRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Purchase intent not found"));
    }

    @Override
    public List<PurchaseIntentRecord> getAllIntents() {
        return purchaseIntentRecordRepository.findAll();
    }
}
