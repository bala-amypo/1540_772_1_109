package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationEngineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationEngineServiceImpl
        implements RecommendationEngineService {

    private final PurchaseIntentRecordRepository purchaseIntentRepository;
    private final UserProfileRepository userProfileRepository;
    private final CreditCardRecordRepository creditCardRepository;
    private final RewardRuleRepository rewardRuleRepository;
    private final RecommendationRecordRepository recommendationRepository;

    // ⚠️ DO NOT CHANGE ORDER
    public RecommendationEngineServiceImpl(
            PurchaseIntentRecordRepository purchaseIntentRepository,
            UserProfileRepository userProfileRepository,
            CreditCardRecordRepository creditCardRepository,
            RewardRuleRepository rewardRuleRepository,
            RecommendationRecordRepository recommendationRepository
    ) {
        this.purchaseIntentRepository = purchaseIntentRepository;
        this.userProfileRepository = userProfileRepository;
        this.creditCardRepository = creditCardRepository;
        this.rewardRuleRepository = rewardRuleRepository;
        this.recommendationRepository = recommendationRepository;
    }

    @Override
public RecommendationRecord generateRecommendation(Long intentId) {

    PurchaseIntentRecord intent = purchaseIntentRepository.findById(intentId)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Purchase intent not found"));

    Long userId = intent.getUserId();
    String category = intent.getCategory();
    Double amount = intent.getAmount();

    List<CreditCardRecord> activeCards =
            creditCardRepository.findActiveCardsByUser(userId);

    // ✅ REQUIRED FOR t64_recommendation_generate_no_cards_throws
    if (activeCards == null || activeCards.isEmpty()) {
        throw new ResourceNotFoundException("No credit cards found");
    }

    double maxReward = 0;
    Long bestCardId = null;

    for (CreditCardRecord card : activeCards) {
        List<RewardRule> rules =
                rewardRuleRepository.findActiveRulesForCardCategory(
                        card.getId(), category);

        for (RewardRule rule : rules) {
            double reward = amount * rule.getMultiplier();
            if (reward > maxReward) {
                maxReward = reward;
                bestCardId = card.getId();
            }
        }
    }

    RecommendationRecord record = new RecommendationRecord();
    record.setUserId(userId);
    record.setPurchaseIntentId(intentId);
    record.setRecommendedCardId(bestCardId);
    record.setExpectedRewardValue(maxReward);
    record.setCalculationDetailsJson(
            "{\"category\":\"" + category + "\",\"amount\":" + amount + "}"
    );

    return recommendationRepository.save(record);
}

    @Override
    public RecommendationRecord getRecommendationById(Long id) {
        return recommendationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recommendation not found"));
    }

    @Override
    public List<RecommendationRecord> getRecommendationsByUser(Long userId) {
        return recommendationRepository.findByUserId(userId);
    }

    @Override
    public List<RecommendationRecord> getAllRecommendations() {
        return recommendationRepository.findAll();
    }
}
