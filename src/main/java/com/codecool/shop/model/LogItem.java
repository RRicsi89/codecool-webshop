package com.codecool.shop.model;

import java.math.BigDecimal;

public class LogItem {

    private final int productId;
    private final String productName;
    private final String description;
    private final String price;
    private final String categories;
    private final String supplier;
    public Integer quantity;
    public BigDecimal totalPrice;

    public LogItem(int productId,
                   String productName,
                   String description,
                   String price,
                   String categories,
                   String supplier,
                   Integer quantity,
                   BigDecimal totalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.categories = categories;
        this.supplier = supplier;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
