package com.codecool.shop.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Order {
    private List<Item> cart;
    private final UUID id = UUID.randomUUID();
    private User user;

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

    public Integer getItemQuantity (Product product) {
        for (Item item: cart) {
            if (item.product.equals(product)) {
                return item.quantity;
            }
        }
        return 0;
    }

    public Integer getItemsQuantity() {
        int quantity = 0;
        for (Item item: cart) {
            quantity += item.quantity;
        }
        return quantity;
    }
    public BigDecimal getItemsTotalValue(Product product) {
        for (Item item: cart) {
            if (item.product.equals(product)) {
                return item.totalPrice;
            }
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getTotalValue() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Item item: cart) {
           totalPrice = totalPrice.add(item.totalPrice);
        }
        return totalPrice;
    }

    public List<Item> getCart () {return cart;}


    public void addProduct(Product product) {
        for (Item item: cart) {
            if (item.product.equals(product)) {
                item.setQuantity(item.quantity + 1);
                return;
            }
        }
        cart.add(new Item(product, 1));
    }


    public void decreaseProductQuantity(Product product) {
        for (Item item: cart) {
            if (item.product.equals(product)) {
                if (item.quantity -1 > 0) {
                    item.setQuantity(item.quantity - 1);
                } else {
                    removeItem(item.product);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static void deleteOrder() {
        instance = null;
    }
}
