package com.codecool.shop.controller;

import com.codecool.shop.config.ConfigParser;
import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;


@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties props = ConfigParser.parseConfigFile("connection.properties");
        ProductDao productDataStore;
        ProductCategoryDao productCategoryDataStore;
        ProductService productService;
        SupplierDaoMem supplierService = SupplierDaoMem.getInstance();

        if (props.getProperty("dao").equals("jdbc")) {
            DataSource dataSource = DatabaseManager.connect();
            productDataStore = ProductDaoJdbc.getInstance(dataSource);
            productCategoryDataStore = (ProductCategoryDaoJdbc) ProductCategoryDaoJdbc.getInstance(dataSource);
            productService = new ProductService(productDataStore, productCategoryDataStore, supplierService);
        } else {
            productDataStore = ProductDaoMem.getInstance();
            productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            productService = new ProductService(productDataStore,productCategoryDataStore);
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("products", productService.getAll());
        context.setVariable("suppliers", supplierService.getAll());
        engine.process("product/index.html", context, resp.getWriter());
        System.out.println(productService.getAll());
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("head");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");
    }

}
