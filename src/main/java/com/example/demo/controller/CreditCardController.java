@RestController
@RequestMapping("/api/cards")
public class CreditCardController {

    private CreditCardService creditCardService;

    public CreditCardController() {}

    public CreditCardController(CreditCardService service) {
        this.creditCardService = service;
    }

    @Autowired
    public void setCreditCardService(CreditCardService service) {
        this.creditCardService = service;
    }

    @PostMapping
    public ResponseEntity<CreditCardRecord> addCard(@RequestBody CreditCardRecord card) {
        return new ResponseEntity<>(
                creditCardService.addCard(card),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCardRecord> updateCard(
            @PathVariable Long id,
            @RequestBody CreditCardRecord card
    ) {
        return ResponseEntity.ok(creditCardService.updateCard(id, card));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CreditCardRecord>> getCardsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(creditCardService.getCardsByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardRecord> getCardById(@PathVariable Long id) {
        return ResponseEntity.ok(creditCardService.getCardById(id));
    }

    @GetMapping
    public ResponseEntity<List<CreditCardRecord>> getAllCards() {
        return ResponseEntity.ok(creditCardService.getAllCards());
    }
}
