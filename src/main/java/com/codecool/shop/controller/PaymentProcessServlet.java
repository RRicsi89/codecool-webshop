package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.log.Logger;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.PaymentValidator;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/process-payment"})
public class PaymentProcessServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");

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
            // TODO send email
            Logger.logToFile(order, true);
            Order.deleteOrder();
            engine.process("product/confirmation.html", context, response.getWriter());
        } else {
            Logger.logToFile(order, false);
            response.sendRedirect("/payment");
        }

    }
}
