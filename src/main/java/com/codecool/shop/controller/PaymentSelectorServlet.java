package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpHeaders;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentSelectorServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Order order = Order.getInstance();
        BigDecimal totalPrice = order.getTotalValue();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("total", totalPrice);
        boolean redirected = false;
        System.out.println(request.getHeader("Referer"));

        if (request.getHeader("Referer").equals("http://localhost:8080/payment")) {
            redirected = true;
        }
        context.setVariable("redirected", redirected);

        engine.process("product/payment.html", context, response.getWriter());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doPost(request, response);
    }

}
