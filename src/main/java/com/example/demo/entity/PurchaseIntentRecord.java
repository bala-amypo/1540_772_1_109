package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PurchaseIntentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Double amount;
    private String category;
    private String merchant;
    private LocalDateTime IntentDate;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getMerchant() {
        return merchant;
    }
    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }
    public LocalDateTime getIntentDate() {
        return IntentDate;
    }
    public void setIntentDate(LocalDateTime intentDate) {
        IntentDate = intentDate;
    }
    public PurchaseIntentRecord() {
    }
    public PurchaseIntentRecord(Long id, Long userId, Double amount, String category, String merchant,
            LocalDateTime intentDate) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.category = category;
        this.merchant = merchant;
        IntentDate = intentDate;
    }
    
}
