package com.codecool.shop.model;

import java.math.BigDecimal;

public class Item {
    public Product product;
    public Integer quantity;
    public BigDecimal totalPrice;

    public Item(Product product, Integer quantity) {
        this.product=product;
        this.quantity = quantity;
        this. totalPrice = BigDecimal.valueOf(quantity).multiply(product.getDefaultPrice());
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        this.totalPrice = BigDecimal.valueOf(quantity).multiply(product.getDefaultPrice());
    }
}
