package com.codecool.shop.model;

public class User {

    private final String name;
    private final String email;
    private final int phoneNumber;
    private final Address billingAddress;
    private final Address shippingAddress;

    public User(String name, String email, int phoneNumber, Address billingAddress, Address shippingAddress) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }
}
