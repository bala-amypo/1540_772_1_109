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

    private RewardRuleRepository repo;
    public RewardRuleServiceImpl(RewardRuleRepository repository) {
    this.rewardRuleRepository = repository;
}


    public RewardRuleServiceImpl() {}

    public RewardRuleServiceImpl(RewardRuleRepository repo) {
        this.repo = repo;
    }

    @Autowired
    public void setRepo(RewardRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public RewardRule createRule(RewardRule rule) {
        return repo.save(rule);
    }

    @Override
    public RewardRule updateRule(Long id, RewardRule updated) {
        RewardRule existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reward rule not found"));
        existing.setCategory(updated.getCategory());
        existing.setRewardType(updated.getRewardType());
        existing.setMultiplier(updated.getMultiplier());
        existing.setActive(updated.getActive());
        return repo.save(existing);
    }

    @Override
    public List<RewardRule> getRulesByCard(Long cardId) {
        return repo.findByCardId(cardId);
    }

    @Override
    public List<RewardRule> getActiveRules() {
        return repo.findByActiveTrue();
    }

    @Override
    public List<RewardRule> getAllRules() {
        return repo.findAll();
    }
}
