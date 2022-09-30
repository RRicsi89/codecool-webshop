package com.codecool.shop.dao;

import com.codecool.shop.model.SessionUser;
import com.codecool.shop.model.User;

import java.util.Map;

public interface UserDao {
    public void registerUser(String name, String password);
    public Map<String, String> getAllUsers();
    public SessionUser getUserBy(String name, String password);
    public void saveUserOrder(int userId, int productId, int quantity);
}
