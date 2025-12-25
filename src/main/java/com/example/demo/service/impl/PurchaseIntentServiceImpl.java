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

    private PurchaseIntentRecordRepository repo;
    public PurchaseIntentServiceImpl(PurchaseIntentRecordRepository repository) {
    this.purchaseIntentRecordRepository = repository;
}


    public PurchaseIntentServiceImpl() {}

    public PurchaseIntentServiceImpl(PurchaseIntentRecordRepository repo) {
        this.repo = repo;
    }

    @Autowired
    public void setRepo(PurchaseIntentRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public PurchaseIntentRecord createIntent(PurchaseIntentRecord intent) {
        return repo.save(intent);
    }

    @Override
    public List<PurchaseIntentRecord> getIntentsByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public PurchaseIntentRecord getIntentById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase intent not found"));
    }

    @Override
    public List<PurchaseIntentRecord> getAllIntents() {
        return repo.findAll();
    }
}
