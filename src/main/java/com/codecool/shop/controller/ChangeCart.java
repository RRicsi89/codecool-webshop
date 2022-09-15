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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/api/changeCart"})
public class ChangeCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("product-id"));
        ProductDaoMem products = ProductDaoMem.getInstance();
        Order cart = Order.getInstance();
        Product product = null;
        if (productId > 0) {
            product = products.find(productId);
            cart.addProductByAddToCart(product);
        } else if (productId < 0) {
            product = products.find(productId * -1);
            cart.descentProductQuantity(product);
        }
        Integer quantity = cart.getItemQuantity(product);
        BigDecimal value = cart.getItemsTotalValue(product);
        BigDecimal totalValue = cart.getTotalValue();
        List<BigDecimal> newData = new ArrayList<>();
        newData.add(BigDecimal.valueOf(quantity));
        newData.add(value);
        newData.add(totalValue);
        String json = new Gson().toJson(newData);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
