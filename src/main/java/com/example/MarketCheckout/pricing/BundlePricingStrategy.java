package com.example.MarketCheckout.pricing;

import com.example.MarketCheckout.model.Cart;
import com.example.MarketCheckout.model.CartItem;
import com.example.MarketCheckout.pricing.rule.BundleDiscountRule;

import java.math.BigDecimal;

public class BundlePricingStrategy implements PricingStrategy {
    private final Cart cart;

    public BundlePricingStrategy(Cart cart) {
        this.cart = cart;
    }

    @Override
    public BigDecimal calculatePrice(CartItem cartItem) {
        BundleDiscountRule rule = cartItem.getItem().getBundleDiscountRule();
        if (rule == null) return null; // Nie obsługujemy tego produktu

        CartItem pairedItem = cart.getItems().get(rule.pairedId());
        if (pairedItem == null) return null; // Brak pary w koszyku

        BigDecimal discount = BigDecimal.valueOf(
                Math.min(cartItem.getQuantity(), pairedItem.getQuantity())
        ).multiply(rule.discount());
        BigDecimal basePrice = cartItem.getItem().getPrice()
                .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        return basePrice.subtract(discount);
    }
}
