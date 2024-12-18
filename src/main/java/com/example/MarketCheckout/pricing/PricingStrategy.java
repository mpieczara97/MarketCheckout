package com.example.MarketCheckout.pricing;

import com.example.MarketCheckout.model.CartItem;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(CartItem cartItem);
}
