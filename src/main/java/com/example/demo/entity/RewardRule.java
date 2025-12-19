package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "reward_rules")
public class RewardRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Long cardId;

    @NotBlank
    @Column(nullable = false)
    private String category;

    @NotBlank
    @Column(nullable = false)
    private String rewardType;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Double multiplier;

    @NotNull
    @Column(nullable = false)
    private Boolean active = true;

    /* Getters and Setters */

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCardId() { return cardId; }
    public void setCardId(Long cardId) { this.cardId = cardId; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getRewardType() { return rewardType; }
    public void setRewardType(String rewardType) { this.rewardType = rewardType; }

    public Double getMultiplier() { return multiplier; }
    public void setMultiplier(Double multiplier) { this.multiplier = multiplier; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
