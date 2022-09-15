package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CategoriesServlet", urlPatterns = {"/categories"})
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameter = request.getParameter("name");
        if (!parameter.equals("default")) {
            ProductDao productDataStore = ProductDaoMem.getInstance();
            ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            ProductService productService = new ProductService(productDataStore, productCategoryDataStore);
            List<Product> products = productService.getProductsForCategory(parameter);
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(products));
        } else {
            ProductDaoMem products = ProductDaoMem.getInstance();
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(products.getAll()));

        }
    }
}
