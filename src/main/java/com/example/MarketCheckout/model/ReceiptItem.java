package com.example.MarketCheckout.model;

import java.math.BigDecimal;

public record ReceiptItem(String name, int quantity, BigDecimal price) {

    @Override
    public String toString() {
        return "ReceiptItem{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
