package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.RewardRuleEntity;

@Service
public interface RewardRuleService {
    RewardRuleEntity createRule(RewardRuleEntity rewardrule);
    Optional<RewardRuleEntity> getRulesByCard(Long cardId);
    List<RewardRuleEntity> getAllRules();
}