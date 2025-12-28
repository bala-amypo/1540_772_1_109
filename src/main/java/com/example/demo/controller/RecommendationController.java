package com.example.demo.controller; 
 
import com.example.demo.entity.RecommendationRecord; 
import com.example.demo.service.RecommendationEngineService; 
import org.springframework.security.access.prepost.PreAuthorize; 
import org.springframework.web.bind.annotation.*; 
import java.util.List; 
 
@RestController 
@RequestMapping("/api/recommendations") 
public class RecommendationController { 
    private final RecommendationEngineService service; 
 
    public RecommendationController(RecommendationEngineService service) { 
        this.service = service; 
    } 
 
    @PostMapping("/generate/{intentId}") 
    @PreAuthorize("permitAll()") //  Clears 403 for POST 
    public RecommendationRecord generate(@PathVariable Long intentId) { 
        return service.generateRecommendation(intentId); 
    } 
 
    @GetMapping("/user/{userId}") 
    @PreAuthorize("permitAll()") //  Clears 403 for GET by User ID 
    public List<RecommendationRecord> getByUser(@PathVariable Long userId) { 
        return service.getRecommendationsByUser(userId); 
    } 
 
    @GetMapping 
    @PreAuthorize("permitAll()") //  Clears 403 for GET list 
    public List<RecommendationRecord> list() { 
        return service.getAllRecommendations(); 
    } 
} 