package com.example.MarketCheckout.pricing.rule;

import java.math.BigDecimal;

public record BundleDiscountRule(long pairedId, BigDecimal discount) {
}
