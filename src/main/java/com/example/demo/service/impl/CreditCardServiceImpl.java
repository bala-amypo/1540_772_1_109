package com.example.demo.service.impl;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CreditCardRecordRepository;
import com.example.demo.service.CreditCardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRecordRepository repository;

    public CreditCardServiceImpl(CreditCardRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreditCardRecord addCard(CreditCardRecord card) {
        if (card.getIsActive() == null) {
            card.setIsActive(true); // ✅ default active
        }
        return repository.save(card);
    }

    @Override
    public CreditCardRecord updateCard(Long id, CreditCardRecord card) {
        CreditCardRecord existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));

        existing.setCardName(card.getCardName());
        existing.setIssuer(card.getIssuer());
        existing.setIsActive(card.getIsActive());

        return repository.save(existing);
    }

    @Override
    public CreditCardRecord getCardById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));
    }

    @Override
    public List<CreditCardRecord> getCardsByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<CreditCardRecord> getAllCards() {
        return repository.findAll();
    }
}
