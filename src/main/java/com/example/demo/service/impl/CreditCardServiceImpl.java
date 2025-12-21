package com.example.demo.service.impl;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CreditCardRecordRepository;
import com.example.demo.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRecordRepository creditCardRecordRepository;

    @Override
    public CreditCardRecord addCard(CreditCardRecord card) {

        if (card.getAnnualFee() < 0) {
            throw new BadRequestException("Annual fee must be non-negative");
        }

        if (card.getUserId() == null) {
            throw new BadRequestException("UserId is required");
        }

        return creditCardRecordRepository.save(card);
    }

    @Override
    public CreditCardRecord updateCard(Long id, CreditCardRecord updated) {

        CreditCardRecord existing = getCardById(id);

        existing.setCardName(updated.getCardName());
        existing.setIssuer(updated.getIssuer());
        existing.setCardType(updated.getCardType());
        existing.setAnnualFee(updated.getAnnualFee());
        existing.setStatus(updated.getStatus());

        return creditCardRecordRepository.save(existing);
    }

    @Override
    public List<CreditCardRecord> getCardsByUser(Long userId) {
        return creditCardRecordRepository.findByUserId(userId);
    }

    @Override
    public CreditCardRecord getCardById(Long id) {
        return creditCardRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));
    }

    @Override
    public List<CreditCardRecord> getAllCards() {
        return creditCardRecordRepository.findAll();
    }
}
