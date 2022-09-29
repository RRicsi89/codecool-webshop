package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoJdbc;
import com.codecool.shop.log.Logger;
import com.codecool.shop.model.Item;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.PaymentValidator;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api/process-payment"})
public class PaymentProcessServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        DataSource dataSource = DatabaseManager.connect();
        UserDao userManager = UserDaoJdbc.getInstance(dataSource);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        Order order = Order.getInstance();
        boolean paymentCheck = false;

        if (request.getParameter("card-number") != null) {
            paymentCheck = PaymentValidator.validateCreditCard(
                    request.getParameter("card-holder"),
                    Integer.parseInt(request.getParameter("card-number")),
                    request.getParameter("expiration"),
                    Integer.parseInt(request.getParameter("cvv")));
        } else {
            paymentCheck = PaymentValidator.validatePayPalAccount(
                    request.getParameter("username"),
                    request.getParameter("password"));
        }

        if (paymentCheck) {
            context.setVariable("cart", order.getCart());
            int userId = ProductController.userId;
            Logger.logToFile(order, true);
            List<Item> items = order.getCart();
            for (Item item: items) {
                int productId = item.getProduct().getId();
                int quantity = item.getQuantity();
                userManager.saveUserOrder(userId, productId, quantity);
            }
            Order.deleteOrder();
            engine.process("product/confirmation.html", context, response.getWriter());
        } else {
            Logger.logToFile(order, false);
            response.sendRedirect("/payment");
        }

    }
}
