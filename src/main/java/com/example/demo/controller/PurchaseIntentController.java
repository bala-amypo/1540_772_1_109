package com.example.demo.controller;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.service.PurchaseIntentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intents")
public class PurchaseIntentController {

    private final PurchaseIntentService purchaseIntentService;

    // ✅ REQUIRED BY TESTS
    public PurchaseIntentController(PurchaseIntentService purchaseIntentService) {
        this.purchaseIntentService = purchaseIntentService;
    }

    @PostMapping
    public ResponseEntity<PurchaseIntentRecord> createIntent(
            @RequestBody PurchaseIntentRecord intent
    ) {
        return new ResponseEntity<>(
                purchaseIntentService.createIntent(intent),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PurchaseIntentRecord>> getIntentsByUser(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(purchaseIntentService.getIntentsByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseIntentRecord> getIntentById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseIntentService.getIntentById(id));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseIntentRecord>> getAllIntents() {
        return ResponseEntity.ok(purchaseIntentService.getAllIntents());
    }
}
