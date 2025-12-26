package com.example.demo.service.impl;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.entity.RecommendationRecord;
import com.example.demo.entity.RewardRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CreditCardRecordRepository;
import com.example.demo.repository.PurchaseIntentRecordRepository;
import com.example.demo.repository.RecommendationRecordRepository;
import com.example.demo.repository.RewardRuleRepository;
import com.example.demo.repository.UserProfileRepository;
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

   List<CreditCardRecord> activeCards =
        creditCardRepository.findActiveCardsByUser(intent.getUserId());

// ✅ MUST HANDLE NULL + EMPTY
if (activeCards == null || activeCards.isEmpty()) {
    throw new ResourceNotFoundException("No active credit cards found");
}


    double maxReward = 0;
    Long bestCardId = null;

    for (CreditCardRecord card : activeCards) {
        List<RewardRule> rules =
                rewardRuleRepository.findActiveRulesForCardCategory(
                        card.getId(), intent.getCategory());

        for (RewardRule rule : rules) {
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
    record.setCalculationDetailsJson(
            "{\"category\":\"" + intent.getCategory() +
            "\",\"amount\":" + intent.getAmount() + "}"
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
