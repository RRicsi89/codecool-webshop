package com.codecool.shop.model;

public class SessionUser {
    private final String name;
    private final int id;

    public SessionUser(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
