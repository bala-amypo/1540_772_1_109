package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.RewardRuleEntity;

public interface RewardRuleRepository extends JpaRepository<RewardRuleEntity,Long>{
    

}