package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.RewardRule;

@Service
public interface RewardRuleService {
    RewardRule createRule(RewardRule rewardrule);
    Optional<RewardRule> getRulesByCard(Long cardId);
    List<RewardRule> getAllRules();
}