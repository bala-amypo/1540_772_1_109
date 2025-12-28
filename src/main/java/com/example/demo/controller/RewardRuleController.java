package com.example.demo.controller; 
 
import com.example.demo.entity.RewardRule; 
import com.example.demo.service.RewardRuleService; 
import org.springframework.security.access.prepost.PreAuthorize; 
import org.springframework.web.bind.annotation.*; 
import java.util.List; 
 
@RestController 
@RequestMapping("/api/reward-rules") 
public class RewardRuleController { 
    private final RewardRuleService service; 
 
    public RewardRuleController(RewardRuleService service) { 
        this.service = service; 
    } 
 
    @PostMapping 
    @PreAuthorize("permitAll()") //  Clears 403 for POST 
    public RewardRule create(@RequestBody RewardRule rule) { 
        return service.createRule(rule); 
    } 
 
    @GetMapping("/active") 
    @PreAuthorize("permitAll()") //  Clears 403 for GET active 
    public List<RewardRule> getActive() { 
        return service.getActiveRules(); 
    } 
 
    @GetMapping 
    @PreAuthorize("permitAll()") //  Clears 403 for GET all 
    public List<RewardRule> list() { 
        return service.getAllRules(); 
    } 
}