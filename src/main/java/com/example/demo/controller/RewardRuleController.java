package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.RewardRule;
import com.example.demo.service.RewardRuleService;

@RestController
public class RewardRuleController {
    @Autowired
    RewardRuleService rs;
    @PostMapping("/Create reward rule")
    public RewardRule create(@RequestBody RewardRule re){
        return rs.createRule(re);
    }
    @GetMapping("/card/{cardId}")
    public Optional<RewardRule> getcard(@PathVariable Long re){
        return rs.getRulesByCard(re);
    }
    @GetMapping("/List all Rules")
    public List<RewardRule> getRules()
    {
        return rs.getAllRules();
    }
}
