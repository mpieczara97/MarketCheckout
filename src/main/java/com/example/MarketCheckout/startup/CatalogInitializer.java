package com.example.MarketCheckout.startup;

import com.example.MarketCheckout.model.Item;
import com.example.MarketCheckout.pricing.rule.BundleDiscountRule;
import com.example.MarketCheckout.pricing.rule.MultiPricingRule;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogInitializer {
    public List<Item> initializeCatalog() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, "Apple", BigDecimal.valueOf(100)));
        items.add(new Item(2, "Banana", BigDecimal.valueOf(50)));
        items.add(new Item(3, "Orange", BigDecimal.valueOf(70)));

        MultiPricingRule appleRule = new MultiPricingRule(3, BigDecimal.valueOf(250));
        items.get(0).setMultiPricingRule(appleRule);

        BundleDiscountRule orangeBananaRule = new BundleDiscountRule(2, BigDecimal.valueOf(20));
        items.get(2).setBundleDiscountRule(orangeBananaRule);

        return items;
    }
}
