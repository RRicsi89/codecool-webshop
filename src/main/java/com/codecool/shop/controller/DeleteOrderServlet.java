package com.codecool.shop.controller;

import com.codecool.shop.log.Logger;
import com.codecool.shop.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/api/order/delete"})
public class DeleteOrderServlet extends HttpServlet {

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");

        Order order = Order.getInstance();
        Logger.logToFile(order, false);
        Order.deleteOrder();
        // TODO implement OrderDaoMem delete method

        PrintWriter out = response.getWriter();
        out.flush();
    }
}
