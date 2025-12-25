@RestController
@RequestMapping("/api/intents")
public class PurchaseIntentController {

    private PurchaseIntentService purchaseIntentService;

    public PurchaseIntentController() {}

    public PurchaseIntentController(PurchaseIntentService service) {
        this.purchaseIntentService = service;
    }

    @Autowired
    public void setPurchaseIntentService(PurchaseIntentService service) {
        this.purchaseIntentService = service;
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
