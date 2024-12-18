package com.example.MarketCheckout.model;

import java.util.List;

public class Receipt {
    private final List<ReceiptItem> items;
    private final int total;

    public Receipt(List<ReceiptItem> items, int total) {
        this.items = items;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "items=" + items +
                ", total=" + total +
                '}';
    }
}
