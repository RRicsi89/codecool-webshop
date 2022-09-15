package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier ricsiShop = new Supplier("RicsiShop", "Life essentials");
        supplierDataStore.add(ricsiShop);
        Supplier dominikShop = new Supplier("DominikShop", "Barely used bodyparts");
        supplierDataStore.add(dominikShop);
        Supplier gaborShop = new Supplier("GaborShop", "Junk");
        supplierDataStore.add(gaborShop);


        //setting up a new product category
        ProductCategory big = new ProductCategory("Big", "Rare", "Something big.");
        productCategoryDataStore.add(big);
        ProductCategory small = new ProductCategory("Small", "Common", "Something small.");
        productCategoryDataStore.add(small);
        ProductCategory expensive = new ProductCategory("Expensive", "Epic", "Something expensive.");
        productCategoryDataStore.add(expensive);
        ProductCategory cheap = new ProductCategory("Cheap", "Common", "Something cheap.");
        productCategoryDataStore.add(cheap);
        ProductCategory useless = new ProductCategory("Useless", "Legendary", "Something useless.");
        productCategoryDataStore.add(useless);
        ProductCategory useful = new ProductCategory("Useful", "Common", "Something small.");
        productCategoryDataStore.add(useful);
        ProductCategory ugly = new ProductCategory("Ugly", "Beautiful", "Something ugly.");
        productCategoryDataStore.add(ugly);
        ProductCategory beautiful = new ProductCategory("Beautiful", "Legendary", "Something beautiful.");
        productCategoryDataStore.add(beautiful);
        ProductCategory booster = new ProductCategory("Booster", "Epic", "Epic boost.");
        productCategoryDataStore.add(booster);
        ProductCategory tiny = new ProductCategory("Tiny", "Common", "Something tiny.");
        productCategoryDataStore.add(tiny);
        ProductCategory colorful = new ProductCategory("Colorful", "Epic", "Something colorful.");
        productCategoryDataStore.add(colorful);


        //setting up products and printing it
        productDataStore.add(new Product("Something Stupid", new BigDecimal("0.99"), "USD", "Description", gaborShop, useless, big, cheap));
        productDataStore.add(new Product("Something Ordinary", new BigDecimal("49.99"), "USD", "Description", gaborShop, useful, cheap, small));
        productDataStore.add(new Product("Something Extraordinary", new BigDecimal("499.99"), "USD", "Description", gaborShop, useful, expensive, big));
        productDataStore.add(new Product("Something Big", new BigDecimal("449.99"), "USD", "Description", dominikShop, tiny, expensive, beautiful));
        productDataStore.add(new Product("Something Funny", new BigDecimal("1199.99"), "USD", "Description", dominikShop, expensive, ugly, useless));
        productDataStore.add(new Product("Something Delightful", new BigDecimal("48.99"), "USD", "Description", dominikShop, beautiful, booster, useful, cheap, colorful));
        productDataStore.add(new Product("Something Lifesaving", new BigDecimal("99.99"), "USD", "Description", ricsiShop, useful, cheap, booster, small));
        productDataStore.add(new Product("Something Sickening", new BigDecimal("199.99"), "USD", "Description", ricsiShop, useful, expensive, colorful, small));
        productDataStore.add(new Product("Something Healthy", new BigDecimal("999.99"), "USD", "Description", ricsiShop, useful, expensive, beautiful, small));

        productDataStore.add(new Product("nyuszi", new BigDecimal("999.99"), "USD", "Description", ricsiShop, beautiful));

    }
}
