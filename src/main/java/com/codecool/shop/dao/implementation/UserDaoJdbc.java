package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserDaoJdbc implements UserDao {
    private DataSource dataSource;
    private static UserDaoJdbc instance = null;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UserDaoJdbc getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new UserDaoJdbc(dataSource);
        }
        return instance;
    }

    @Override
    public void registerUser(String name, String password) {
        try(Connection connection = dataSource.getConnection()) {
            String SQL = "INSERT INTO users " +
                    "VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, name);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, String> getAllUsers() {
        try(Connection connection = dataSource.getConnection()) {
            String SQL = "SELECT name, password FROM users";
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            Map<String, String> result = new HashMap<>();

            while (resultSet.next()) {
                result.put(resultSet.getString(1), resultSet.getString(2));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
