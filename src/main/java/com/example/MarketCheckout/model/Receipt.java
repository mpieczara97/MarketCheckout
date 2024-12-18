package com.example.MarketCheckout.model;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {
    private final List<ReceiptItem> items;
    private final BigDecimal total;

    public Receipt(List<ReceiptItem> items, BigDecimal total) {
        this.items = items;
        this.total = total;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
