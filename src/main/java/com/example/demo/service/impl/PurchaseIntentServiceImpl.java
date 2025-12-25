package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PurchaseIntentRecordRepository;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.PurchaseIntentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseIntentServiceImpl implements PurchaseIntentService {

    private final PurchaseIntentRecordRepository purchaseIntentRepository;
    private final UserProfileRepository userProfileRepository;

    public PurchaseIntentServiceImpl(
            PurchaseIntentRecordRepository purchaseIntentRepository,
            UserProfileRepository userProfileRepository
    ) {
        this.purchaseIntentRepository = purchaseIntentRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public PurchaseIntentRecord createPurchaseIntent(PurchaseIntentRecord intent) {
        // ✅ REQUIRED
        userProfileRepository.findById(intent.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return purchaseIntentRepository.save(intent);
    }

    @Override
    public List<PurchaseIntentRecord> getByUser(Long userId) {
        return purchaseIntentRepository.findByUserId(userId);
    }

    @Override
    public PurchaseIntentRecord getById(Long id) {
        return purchaseIntentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase intent not found"));
    }
}
