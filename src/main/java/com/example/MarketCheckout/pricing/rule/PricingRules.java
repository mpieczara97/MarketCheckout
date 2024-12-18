package com.example.MarketCheckout.pricing.rule;

import com.example.MarketCheckout.model.Cart;
import com.example.MarketCheckout.model.CartItem;
import com.example.MarketCheckout.pricing.BundlePricingStrategy;
import com.example.MarketCheckout.pricing.MultiPricingStrategy;
import com.example.MarketCheckout.pricing.PricingStrategy;
import com.example.MarketCheckout.pricing.StandardPricingStrategy;

import java.math.BigDecimal;
import java.util.List;

public class PricingRules {
    private final List<PricingStrategy> strategies;

    public PricingRules(Cart cart) {
        this.strategies = List.of(
                new MultiPricingStrategy(),
                new BundlePricingStrategy(cart),
                new StandardPricingStrategy()
        );
    }

    public BigDecimal calculatePrice(CartItem cartItem) {
        for (PricingStrategy strategy : strategies) {
            BigDecimal price = strategy.calculatePrice(cartItem);
            if (price != null) {
                return price;
            }
        }
        return BigDecimal.ZERO;
    }
}
