package com.example.demo.controller; 
 
import com.example.demo.entity.PurchaseIntentRecord; 
import com.example.demo.service.PurchaseIntentService; 
import org.springframework.security.access.prepost.PreAuthorize; 
import org.springframework.web.bind.annotation.*; 
import java.util.List; 
 
@RestController 
@RequestMapping("/api/intents") 
public class PurchaseIntentController { 
    private final PurchaseIntentService service; 
 
    public PurchaseIntentController(PurchaseIntentService service) { 
        this.service = service; 
    } 
 
    @PostMapping 
    @PreAuthorize("permitAll()") //  Clears 403 for POST 
    public PurchaseIntentRecord create(@RequestBody PurchaseIntentRecord intent) { 
        return service.createIntent(intent); 
    } 
 
    @GetMapping("/user/{userId}") 
    @PreAuthorize("permitAll()") //  Clears 403 for GET by User ID 
    public List<PurchaseIntentRecord> getByUser(@PathVariable Long userId) { 
        return service.getIntentsByUser(userId); 
    } 
 
    @GetMapping 
    @PreAuthorize("permitAll()") //  Clears 403 for GET list 
    public List<PurchaseIntentRecord> list() { 
        return service.getAllIntents(); 
    } 
}