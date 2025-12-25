package com.example.demo.service.impl;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CreditCardRecordRepository;
import com.example.demo.service.CreditCardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRecordRepository creditCardRepository;

    public CreditCardServiceImpl(CreditCardRecordRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public CreditCardRecord addCreditCard(CreditCardRecord card) {
        // ✅ REQUIRED BY TEST
        if (card.getActiveFlag() == null) {
            card.setActiveFlag(true);
        }
        return creditCardRepository.save(card);
    }

    @Override
    public CreditCardRecord updateCreditCard(Long id, CreditCardRecord updated) {
        CreditCardRecord existing = creditCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));

        existing.setCardName(updated.getCardName());
        existing.setAnnualFee(updated.getAnnualFee());
        existing.setActiveFlag(updated.getActiveFlag());

        return creditCardRepository.save(existing);
    }

    @Override
    public CreditCardRecord getById(Long id) {
        return creditCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));
    }

    @Override
    public List<CreditCardRecord> getCardsByUser(Long userId) {
        return creditCardRepository.findByUserId(userId);
    }

    @Override
    public List<CreditCardRecord> getAllCards() {
        return creditCardRepository.findAll();
    }
}
