package com.codecool.shop.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/process-payment"})
public class PaymentProcessServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        Enumeration<String> params = request.getParameterNames();
        List<String> paramNames = new LinkedList<>();
        boolean paymentAccepted = false;

        while (params.hasMoreElements()) {
            String param = params.nextElement();
            paramNames.add(param);
        }

        if (paramNames.size() > 2) {
            paymentAccepted = true;
        }

        if (paymentAccepted) {
            response.sendRedirect("/");
        } else {
            response.sendRedirect("payment");
        }

    }
}
