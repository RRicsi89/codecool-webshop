package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ProductDaoJdbc implements ProductDao {
    private DataSource dataSource;
    private static ProductDaoJdbc instance = null;
    ProductCategoryDaoJdbc productCategoryDaoJdbc;

    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
        this.productCategoryDaoJdbc = new ProductCategoryDaoJdbc(dataSource);
    }

    public static ProductDaoJdbc getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new ProductDaoJdbc(dataSource);
        }
        return instance;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        try(Connection connection = dataSource.getConnection()) {
            String SQL = "SELECT DISTINCT * FROM products " +
                    "JOIN product_categories AS pc " +
                    "ON products.id = pc.product_id ";

            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            Set<Product> products = new HashSet<>();

            while (resultSet.next()) {
                Set<ProductCategory> categories = new HashSet<>();
                int productID = resultSet.getInt(1);
                List<Integer> ids = getAllCategoryIdByProductId(productID);
                ids.forEach(id -> categories.add(productCategoryDaoJdbc.getCategoryBy(id)));
                int n = categories.size();
                ProductCategory[] productCategories = new ProductCategory[n];
                Supplier supplier = getSupplierById(resultSet.getInt(5));
                System.arraycopy(categories.toArray(), 0, productCategories, 0, n);
                Product newProduct = new Product(
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getString(4),
                        resultSet.getString(6),
                        supplier,
                        productCategories
                );
                newProduct.setId(productID);
                products.add(newProduct);
            }
            return new LinkedList<>(products);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        try(Connection connection = dataSource.getConnection()) {
            String SQL = "SELECT * FROM products " +
                    "JOIN product_categories AS pc " +
                    "ON products.id = pc.product_id " +
                    "JOIN suppliers s on products.supplier_id = s.id " +
                    "WHERE pc.category_id = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, productCategory.getId());
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new LinkedList<>();
            while (resultSet.next()) {
                Set<ProductCategory> categories = new HashSet<>();
                int productID = resultSet.getInt(1);
                List<Integer> ids = getAllCategoryIdByProductId(productID);
                ids.forEach(id -> categories.add(productCategoryDaoJdbc.getCategoryBy(id)));
                int n = categories.size();
                ProductCategory[] productCategories = new ProductCategory[n];
                System.arraycopy(categories.toArray(), 0, productCategories, 0, n);
                Supplier supplier = getSupplierById(resultSet.getInt(5));
                Product newProduct = new Product(
                        resultSet.getString(2),
                        resultSet.getBigDecimal(3),
                        resultSet.getString(4),
                        resultSet.getString(7),
                        supplier,
                        productCategories
                );
                newProduct.setId(productID);
                products.add(newProduct);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Supplier getSupplierById(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String SQL = "SELECT * FROM suppliers " +
                    "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            Supplier supplier = null;
            while (resultSet.next()) {
                supplier = new Supplier(
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
            }
            return supplier;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Integer> getAllCategoryIdByProductId(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String SQL = "SELECT category_id FROM product_categories " +
                    "WHERE category_id = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Integer> ids = new LinkedList<>();
            while (resultSet.next()) {
                ids.add(resultSet.getInt(1));
            }
            return ids;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
    public List<String> getNames() {
        return null;
    }
}
