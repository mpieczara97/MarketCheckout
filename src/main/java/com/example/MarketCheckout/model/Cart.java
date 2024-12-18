package com.example.MarketCheckout.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<String, CartItem> items = new HashMap<>();

    public void addItem(Item item) {
        items.computeIfAbsent(item.getSku(), k -> new CartItem(item)).incrementQuantity();
    }

    public Map<String, CartItem> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }
}
