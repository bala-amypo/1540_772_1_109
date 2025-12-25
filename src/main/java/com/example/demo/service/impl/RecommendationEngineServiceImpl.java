@Override
public RecommendationRecord generateRecommendation(Long intentId) {

    PurchaseIntentRecord intent = purchaseIntentRepository.findById(intentId)
            .orElseThrow(() -> new ResourceNotFoundException("Purchase intent not found"));

    Long userId = intent.getUserId();
    String category = intent.getCategory();
    Double amount = intent.getAmount();

    List<CreditCardRecord> activeCards =
            creditCardRepository.findActiveCardsByUser(userId);

    // ✅ REQUIRED BY TEST t64
    if (activeCards.isEmpty()) {
        throw new ResourceNotFoundException("No active credit cards found");
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
