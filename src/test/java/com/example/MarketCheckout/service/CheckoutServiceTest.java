package com.example.MarketCheckout.service;

import com.example.MarketCheckout.startup.CatalogInitializer;
import com.example.MarketCheckout.model.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CheckoutServiceTest {

    private CheckoutService checkoutService;

    @BeforeEach
    void setUp() {
        CatalogInitializer catalogInitializer = new CatalogInitializer();
        checkoutService = new CheckoutService(catalogInitializer);
    }

    @Test
    void testScanItemAddsItemToCart() {
        checkoutService.scanItem(1); // Apple
        assertEquals(BigDecimal.valueOf(100), checkoutService.calculateTotal());
    }

    @Test
    void testCalculateTotalWithMultiPricingRule() {
        checkoutService.scanItem(1); // Apple
        checkoutService.scanItem(1);
        checkoutService.scanItem(1); // 3 Apples with multi-pricing rule: 250

        assertEquals(BigDecimal.valueOf(250), checkoutService.calculateTotal());
    }

    @Test
    void testCalculateTotalWithBundleDiscountRule() {
        checkoutService.scanItem(3); // Orange
        checkoutService.scanItem(2); // Banana

        // Orange (70) + Banana (50) - Bundle discount (20) = 100
        assertEquals(BigDecimal.valueOf(100), checkoutService.calculateTotal());
    }

    @Test
    void testFinalizeCheckoutClearsCart() {
        checkoutService.scanItem(1);
        checkoutService.finalizeCheckout();

        assertEquals(BigDecimal.ZERO, checkoutService.calculateTotal());
    }

    @Test
    void testReceiptGeneration() {
        checkoutService.scanItem(1); // Apple
        checkoutService.scanItem(2); // Banana
        Receipt receipt = checkoutService.finalizeCheckout();

        assertEquals(2, receipt.getItems().size());
        assertEquals(BigDecimal.valueOf(150), receipt.getTotal());
    }
}