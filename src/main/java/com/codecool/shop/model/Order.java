package com.codecool.shop.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Order {
    private List<Item> cart;
    private final UUID id = UUID.randomUUID();

    private static Order instance = null;

    private Order () {
        this.cart = new ArrayList<>();
    }

    public static Order getInstance() {
        if (instance == null) {
            instance = new Order();
        }
        return instance;
    }

    public int getItemQuantity() {
        int quantity = 0;
        for (Item item: cart) {
            quantity += item.quantity;
        }
        return quantity;
    }

    public BigDecimal getTotalValue() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Item item: cart) {
           totalPrice = totalPrice.add(item.totalPrice);
        }
        return totalPrice;
    }

    public List<Item> getCart () {return cart;}


    public void addProductByAddToCart(Product product) {
        for (Item item: cart) {
            if (item.product.equals(product)) {
                item.setQuantity(item.quantity + 1);
                return;
            }
        }
        cart.add(new Item(product, 1));
    }


    public void changeProductQuantity(Product product, Integer quantity) {
        for (Item item: cart) {
            if (item.product.equals(product)) {
                if (quantity > 0) {
                    item.setQuantity(quantity);
                } else {
                    cart.remove(item);
                }
                return;
            }
        }
    }

    public void removeItem(Product product) {
        for (Item item: cart) {
            if (item.product.equals(product)) {
                cart.remove(item);
                return;
            }
        }
    }

    public UUID getId() {
        return id;
    }
}
