package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {
    DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {
        try(Connection connection = dataSource.getConnection()) {
            String SQL = "INSERT INTO categories" +
                    "(name, department_id, description)" +
                    "VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, category.getName());
            statement.setInt(2, getDepartmentIdByName(category.getDepartment()));
            statement.setString(3, category.getDescription());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getDepartmentIdByName(String name) {
        try(Connection connection = dataSource.getConnection()) {
            String SQL = "SELECT id FROM departments" +
                    " WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductCategory find(String name) {
        return null;
    }

    @Override
    public void remove(String name) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }
}
