package com.codecool.shop.controller;

import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoJdbc;
import com.codecool.shop.model.SessionUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String hashedPassword = String.valueOf(password.hashCode());

        DataSource dataSource = DatabaseManager.connect();
        UserDao userManager = UserDaoJdbc.getInstance(dataSource);
        SessionUser sessionUser = userManager.getUserBy(name, hashedPassword);

        if (sessionUser != null) {
            ProductController.userName = sessionUser.getName();
            ProductController.userId = sessionUser.getId();
        }
        resp.sendRedirect("index");
    }
}
