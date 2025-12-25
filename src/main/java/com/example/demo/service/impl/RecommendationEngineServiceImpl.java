package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationEngineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationEngineServiceImpl implements RecommendationEngineService {

    private final PurchaseIntentRecordRepository purchaseIntentRepository;
    private final UserProfileRepository userProfileRepository;
    private final CreditCardRecordRepository creditCardRepository;
    private final RewardRuleRepository rewardRuleRepository;
    private final RecommendationRecordRepository recommendationRepository;

    public RecommendationEngineServiceImpl(
            PurchaseIntentRecordRepository purchaseIntentRepository,
            UserProfileRepository userProfileRepository,
            CreditCardRecordRepository creditCardRepository,
            RewardRuleRepository rewardRuleRepository,
            RecommendationRecordRepository recommendationRepository) {
        this.purchaseIntentRepository = purchaseIntentRepository;
        this.userProfileRepository = userProfileRepository;
        this.creditCardRepository = creditCardRepository;
        this.rewardRuleRepository = rewardRuleRepository;
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public RecommendationRecord generateRecommendation(Long intentId) {

        PurchaseIntentRecord intent = purchaseIntentRepository.findById(intentId)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase intent not found"));

        List<CreditCardRecord> cards =
                creditCardRepository.findActiveCardsByUser(intent.getUserId());

        // ✅ REQUIRED FOR TEST
        if (cards.isEmpty()) {
            throw new ResourceNotFoundException("No active credit cards found");
        }

        double maxReward = 0;
        Long bestCardId = null;

        for (CreditCardRecord card : cards) {
            for (RewardRule rule :
                    rewardRuleRepository.findActiveRulesForCardCategory(
                            card.getId(), intent.getCategory())) {

                double reward = intent.getAmount() * rule.getMultiplier();
                if (reward > maxReward) {
                    maxReward = reward;
                    bestCardId = card.getId();
                }
            }
        }

        RecommendationRecord record = new RecommendationRecord();
        record.setUserId(intent.getUserId());
        record.setPurchaseIntentId(intentId);
        record.setRecommendedCardId(bestCardId);
        record.setExpectedRewardValue(maxReward);

        return recommendationRepository.save(record);
    }
}
