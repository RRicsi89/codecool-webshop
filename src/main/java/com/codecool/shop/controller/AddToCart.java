package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/api/addToCart"})
public class AddToCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("product-id"));
        ProductDaoMem products = ProductDaoMem.getInstance();
        Order cart = Order.getInstance();
        if (productId > 0) {
            Product product = products.find(productId);
            cart.addProduct(product);
        } else if (productId < 0) {
            Product product = products.find(productId * -1);
            cart.decreaseProductQuantity(product);
        }
        Integer quantity = cart.getItemsQuantity();
        String json = new Gson().toJson(quantity);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);


    }
}
