package com.example.MarketCheckout.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<Long, CartItem> items = new HashMap<>();

    public void addItem(Item item) {
        items.computeIfAbsent(item.getId(), k -> new CartItem(item)).incrementQuantity();
    }

    public Map<Long, CartItem> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }
}
