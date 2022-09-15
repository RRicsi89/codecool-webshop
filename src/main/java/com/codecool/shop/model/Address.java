package com.codecool.shop.model;

public class Address {

    private final String country;
    private final String city;
    private final int zipcode;
    private final String address;

    public Address(String country, String city, int zipcode, String address) {
        this.country = country;
        this.city = city;
        this.zipcode = zipcode;
        this.address = address;
    }
}
