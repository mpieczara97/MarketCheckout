package com.example.MarketCheckout.service;

import com.example.MarketCheckout.startup.CatalogInitializer;
import com.example.MarketCheckout.pricing.rule.PricingRules;
import com.example.MarketCheckout.model.Cart;
import com.example.MarketCheckout.model.Item;
import com.example.MarketCheckout.model.Receipt;
import com.example.MarketCheckout.model.ReceiptItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CheckoutService {
    private final Cart cart = new Cart();
    private final PricingRules pricingRules;
    private final List<Item> itemCatalog;

    public CheckoutService(CatalogInitializer initializer) {
        this.itemCatalog = initializer.initializeCatalog();
        this.pricingRules = new PricingRules(cart);
    }

    public void scanItem(long id) {
        Item item = itemCatalog.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        cart.addItem(item);
    }

    public BigDecimal calculateTotal() {
        return cart.getItems().values().stream()
                .map(pricingRules::calculatePrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Receipt finalizeCheckout() {
        BigDecimal total = calculateTotal();
        List<ReceiptItem> receiptItems = cart.getItems().values().stream()
                .map(cartItem -> new ReceiptItem(
                        cartItem.getItem().getName(),
                        cartItem.getQuantity(),
                        pricingRules.calculatePrice(cartItem)
                ))
                .toList();
        cart.clear();
        return new Receipt(receiptItems, total);
    }
}
