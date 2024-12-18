package com.example.MarketCheckout.pricing;

import com.example.MarketCheckout.model.CartItem;
import com.example.MarketCheckout.pricing.rule.MultiPricingRule;

import java.math.BigDecimal;

public class MultiPricingStrategy implements PricingStrategy {

    @Override
    public BigDecimal calculatePrice(CartItem cartItem) {
        MultiPricingRule rule = cartItem.getItem().getMultiPricingRule();
        if (rule == null) return null;

        int quantity = cartItem.getQuantity();
        BigDecimal dealPrice = BigDecimal.valueOf(quantity / rule.quantity()).multiply(rule.dealPrice());
        BigDecimal remainingPrice = BigDecimal.valueOf(quantity % rule.quantity()).multiply(cartItem.getItem().getPrice());
        return dealPrice.add(remainingPrice);
    }
}
