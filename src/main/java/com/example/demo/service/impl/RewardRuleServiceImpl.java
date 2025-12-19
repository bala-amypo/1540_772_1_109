package com.example.demo.service.impl;

import com.example.demo.entity.RewardRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RewardRuleRepository;
import com.example.demo.service.RewardRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardRuleServiceImpl implements RewardRuleService {

    @Autowired
    private RewardRuleRepository rewardRuleRepository;

    @Override
    public RewardRule createRule(RewardRule rule) {

        if (rule.getMultiplier() == null || rule.getMultiplier() <= 0) {
            throw new BadRequestException("Multiplier must be greater than zero");
        }

        return rewardRuleRepository.save(rule);
    }

    @Override
    public RewardRule updateRule(Long id, RewardRule updated) {

        RewardRule existing = rewardRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reward rule not found"));

        if (updated.getMultiplier() != null && updated.getMultiplier() <= 0) {
            throw new BadRequestException("Multiplier must be greater than zero");
        }

        existing.setCategory(updated.getCategory());
        existing.setRewardType(updated.getRewardType());
        existing.setMultiplier(updated.getMultiplier());
        existing.setActive(updated.getActive());

        return rewardRuleRepository.save(existing);
    }

    @Override
    public List<RewardRule> getRulesByCard(Long cardId) {
        return rewardRuleRepository.findByCardId(cardId);
    }

    @Override
    public List<RewardRule> getActiveRules() {
        return rewardRuleRepository.findByActiveTrue();
    }

    @Override
    public List<RewardRule> getAllRules() {
        return rewardRuleRepository.findAll();
    }
}
