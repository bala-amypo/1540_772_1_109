package com.example.demo.controller;

import com.example.demo.entity.RewardRule;
import com.example.demo.service.RewardRuleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reward-rules")
public class RewardRuleController {

    private RewardRuleService rewardRuleService;

    public RewardRuleController() {}

    public RewardRuleController(RewardRuleService service) {
        this.rewardRuleService = service;
    }

    @Autowired
    public void setRewardRuleService(RewardRuleService service) {
        this.rewardRuleService = service;
    }

    @PostMapping
    public ResponseEntity<RewardRule> createRule(@RequestBody RewardRule rule) {
        return new ResponseEntity<>(
                rewardRuleService.createRule(rule),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RewardRule> updateRule(
            @PathVariable Long id,
            @RequestBody RewardRule rule
    ) {
        return ResponseEntity.ok(rewardRuleService.updateRule(id, rule));
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<List<RewardRule>> getRulesByCard(
            @PathVariable Long cardId
    ) {
        return ResponseEntity.ok(
                rewardRuleService.getRulesByCard(cardId)
        );
    }

    @GetMapping("/active")
    public ResponseEntity<List<RewardRule>> getActiveRules() {
        return ResponseEntity.ok(rewardRuleService.getActiveRules());
    }

    @GetMapping
    public ResponseEntity<List<RewardRule>> getAllRules() {
        return ResponseEntity.ok(rewardRuleService.getAllRules());
    }
}
