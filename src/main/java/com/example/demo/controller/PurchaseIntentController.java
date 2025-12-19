package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.service.PurchaseIntentService;

@RestController
public class PurchaseIntentController {
    @Autowired
    PurchaseIntentService ps;
    @PostMapping("/rewardrule")
    public PurchaseIntentRecord create(@RequestBody PurchaseIntentRecord pe){
        return ps.createIntent(pe);
    }
    @GetMapping("/user/{userId}")
    public Optional<PurchaseIntentRecord> get(@PathVariable Long re){
        return ps.getIntentByUser(re);
    }
    @GetMapping("/Id/{id}")
    public Optional<PurchaseIntentRecord> getid(@PathVariable Long re){
        return ps.getIntentById(re);
    }
    @GetMapping("/all")
    public List<PurchaseIntentRecord> getall(){
        return ps.getAllIntents();
    }
}
