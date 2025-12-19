package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RewardRule;
import com.example.demo.repository.RewardRuleRepository;

@Service
public class RewardRuleServiceImpl implements RewardRuleService {
    @Autowired
    RewardRuleRepository rr;
    @Override
    public RewardRule createRule(RewardRule rewardrule) {
        return rr.save(rewardrule);
    }
    public Optional<RewardRule> getRulesByCard(Long cardId){
        return rr.findById(cardId);
    }
    public List<RewardRule> getAllRules(){
        return rr.findAll();
    }

}