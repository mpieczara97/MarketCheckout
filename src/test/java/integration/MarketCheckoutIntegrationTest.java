package integration;

import com.example.MarketCheckout.MarketCheckoutApplication;
import com.example.MarketCheckout.controller.CheckoutController;
import com.example.MarketCheckout.model.Receipt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MarketCheckoutApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MarketCheckoutIntegrationTest {

    @Autowired
    private CheckoutController checkoutController;

    @Test
    void testCheckoutWithBundlePricingRule() {
        // Item scan
        checkoutController.scanItem(1); // Apple
        checkoutController.scanItem(2); // Banana
        checkoutController.scanItem(3); // Orange

        // Get total
        ResponseEntity<Map<String, BigDecimal>> totalResponse = checkoutController.getTotal();
        BigDecimal total = totalResponse.getBody().get("total");

        // Check total
        assertEquals(BigDecimal.valueOf(200), total); // Apple (100) + Banana (50) + Orange (70)

        // Finalize
        ResponseEntity<Receipt> receiptResponse = checkoutController.completeCheckout();
        Receipt receipt = receiptResponse.getBody();

        // Check receipt
        assertEquals(3, receipt.getItems().size());
        assertEquals(BigDecimal.valueOf(200), receipt.getTotal());
    }

    @Test
    void testCheckoutWithBothPricingRules() {
        // Item scan
        checkoutController.scanItem(1); // Apple
        checkoutController.scanItem(1); // Apple
        checkoutController.scanItem(1); // Apple
        checkoutController.scanItem(3); // Orange
        checkoutController.scanItem(2); // Banana

        // Get total
        ResponseEntity<Map<String, BigDecimal>> totalResponse = checkoutController.getTotal();
        BigDecimal total = totalResponse.getBody().get("total");

        // Apple (3 for 250) + Orange (70) + Banana (50) - Bundle discount (20) = 350
        assertEquals(BigDecimal.valueOf(350), total);

        // Finalize
        ResponseEntity<Receipt> receiptResponse = checkoutController.completeCheckout();
        Receipt receipt = receiptResponse.getBody();

        // Check receipt
        assertEquals(3, receipt.getItems().size());
        assertEquals(BigDecimal.valueOf(350), receipt.getTotal());
    }
}