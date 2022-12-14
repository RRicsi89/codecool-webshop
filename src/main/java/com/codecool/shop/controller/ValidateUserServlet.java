package com.codecool.shop.controller;

import com.codecool.shop.model.Address;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/user-validation"})
public class ValidateUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        int phoneNumber = Integer.parseInt(request.getParameter("phone"));
        Address billing = new Address(request.getParameter("b-country"),
                request.getParameter("b-city"),
                Integer.parseInt(request.getParameter("b-zipcode")),
                request.getParameter("b-address"));
        Address shipping;

        if (request.getParameter("sh-country").equals("")
                || request.getParameter("sh-city").equals("")
                || request.getParameter("sh-zipcode").equals("")
                || request.getParameter("sh-address").equals("")) {
            shipping = billing;
        } else {
            shipping = new Address(request.getParameter("sh-country"),
                    request.getParameter("sh-city"),
                    Integer.parseInt(request.getParameter("sh-zipcode")),
                    request.getParameter("sh-address"));
        }

        User user = new User(username, email, phoneNumber, billing, shipping);

        Order order = Order.getInstance();
        order.setUser(user);

        response.sendRedirect("/payment");
    }
}
