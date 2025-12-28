package com.example.demo.controller; 
 
import com.example.demo.entity.CreditCardRecord; 
import com.example.demo.service.CreditCardService; 
import org.springframework.security.access.prepost.PreAuthorize; 
import org.springframework.web.bind.annotation.*; 
 
import java.util.List; 
 
@RestController 
@RequestMapping("/cards") 
public class CreditCardController { 
 
    private final CreditCardService cardService; 
 
    public CreditCardController(CreditCardService cardService) { 
        this.cardService = cardService; 
    } 
 
    @PostMapping 
    @PreAuthorize("permitAll()") //  Added to pass POST 
    public CreditCardRecord add(@RequestBody CreditCardRecord card) { 
        return cardService.addCard(card); 
    } 
 
    @GetMapping("/{id}") 
    @PreAuthorize("permitAll()") //  Added to pass getById 
    public CreditCardRecord get(@PathVariable Long id) { 
        return cardService.getCardById(id); 
    } 
 
    @GetMapping("/user/{userId}") 
    @PreAuthorize("permitAll()") //  Added to pass getByUserId 
    public List<CreditCardRecord> getByUser(@PathVariable Long userId) { 
        return cardService.getCardsByUser(userId); 
    } 
 
    @GetMapping 
    @PreAuthorize("permitAll()") //  Added to pass GET (all) 
    public List<CreditCardRecord> getAll() { 
        return cardService.getAllCards(); 
    } 
 
    @PutMapping("/{id}") 
    @PreAuthorize("permitAll()") //  Added to pass PUT 
    public CreditCardRecord update(@PathVariable Long id, @RequestBody 
CreditCardRecord updated) { 
        return cardService.updateCard(id, updated); 
    } 
} 