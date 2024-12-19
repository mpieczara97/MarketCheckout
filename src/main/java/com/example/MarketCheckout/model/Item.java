package com.example.MarketCheckout.model;

import com.example.MarketCheckout.pricing.rule.BundleDiscountRule;
import com.example.MarketCheckout.pricing.rule.MultiPricingRule;

import java.math.BigDecimal;

public class Item {
    private final long id;
    private final String name;
    private final BigDecimal price;
    private MultiPricingRule multiPricingRule;
    private BundleDiscountRule bundleDiscountRule;

    public Item(long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public MultiPricingRule getMultiPricingRule() {
        return multiPricingRule;
    }

    public void setMultiPricingRule(MultiPricingRule multiPricingRule) {
        this.multiPricingRule = multiPricingRule;
    }

    public BundleDiscountRule getBundleDiscountRule() {
        return bundleDiscountRule;
    }

    public void setBundleDiscountRule(BundleDiscountRule bundleDiscountRule) {
        this.bundleDiscountRule = bundleDiscountRule;
    }
}
