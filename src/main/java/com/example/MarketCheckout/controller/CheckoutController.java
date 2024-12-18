package com.example.MarketCheckout.controller;

import com.example.MarketCheckout.model.Receipt;
import com.example.MarketCheckout.service.CheckoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/scan")
    public ResponseEntity<String> scanItem(@RequestParam long id) {
        checkoutService.scanItem(id);
        return ResponseEntity.ok("Item added to cart.");
    }

    @GetMapping("/total")
    public ResponseEntity<Map<String, BigDecimal>> getTotal() {
        BigDecimal total = checkoutService.calculateTotal();
        return ResponseEntity.ok(Map.of("total", total));
    }

    @PostMapping("/complete")
    public ResponseEntity<Receipt> completeCheckout() {
        Receipt receipt = checkoutService.finalizeCheckout();
        return ResponseEntity.ok(receipt);
    }
}
