package com.codecool.shop.dao;

import java.util.Map;

public interface UserDao {
    public void registerUser(String name, String password);
    public Map<String, String> getAllUsers();
}
