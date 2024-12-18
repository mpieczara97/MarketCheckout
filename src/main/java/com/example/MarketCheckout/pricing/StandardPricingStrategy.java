package com.example.MarketCheckout.pricing;

import com.example.MarketCheckout.model.CartItem;

import java.math.BigDecimal;

public class StandardPricingStrategy implements PricingStrategy {
    @Override
    public BigDecimal calculatePrice(CartItem cartItem) {
        return cartItem.getItem().getPrice()
                .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }
}
