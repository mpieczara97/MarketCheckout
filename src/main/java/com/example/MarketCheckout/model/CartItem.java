package com.example.MarketCheckout.model;

public class CartItem {
    private final Item item;
    private int quantity;

    public CartItem(Item item) {
        this.item = item;
        this.quantity = 0;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}
