package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RecommendationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userid;
    private Long purchaseIntentId;
    private Long recommendedCardId;
    private Double expectedRewardValue;
    private String calculationDetailsJson;
    private LocalDateTime recommendedAt;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public Long getPurchaseIntentId() {
        return purchaseIntentId;
    }
    public void setPurchaseIntentId(Long purchaseIntentId) {
        this.purchaseIntentId = purchaseIntentId;
    }
    public Long getRecommendedCardId() {
        return recommendedCardId;
    }
    public void setRecommendedCardId(Long recommendedCardId) {
        this.recommendedCardId = recommendedCardId;
    }
    public Double getExpectedRewardValue() {
        return expectedRewardValue;
    }
    public void setExpectedRewardValue(Double expectedRewardValue) {
        this.expectedRewardValue = expectedRewardValue;
    }
    public String getCalculationDetailsJson() {
        return calculationDetailsJson;
    }
    public void setCalculationDetailsJson(String calculationDetailsJson) {
        this.calculationDetailsJson = calculationDetailsJson;
    }
    public LocalDateTime getRecommendedAt() {
        return recommendedAt;
    }
    public void setRecommendedAt(LocalDateTime recommendedAt) {
        this.recommendedAt = recommendedAt;
    }
    public RecommendationRecord() {
    }
    public RecommendationRecord(Long id, Long userid, Long purchaseIntentId, Long recommendedCardId,
            Double expectedRewardValue, String calculationDetailsJson, LocalDateTime recommendedAt) {
        this.id = id;
        this.userid = userid;
        this.purchaseIntentId = purchaseIntentId;
        this.recommendedCardId = recommendedCardId;
        this.expectedRewardValue = expectedRewardValue;
        this.calculationDetailsJson = calculationDetailsJson;
        this.recommendedAt = recommendedAt;
    }
}
