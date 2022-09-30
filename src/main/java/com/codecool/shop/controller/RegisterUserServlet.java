package com.codecool.shop.controller;

import com.codecool.shop.config.ConfigParser;
import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@WebServlet(urlPatterns = {"/register"})
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties props = ConfigParser.parseConfigFile("connection.properties");
        DataSource dataSource = DatabaseManager.connect();
        UserDao userManager = UserDaoJdbc.getInstance(dataSource);
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String hashedPassword = String.valueOf(password.hashCode());

        if (props.getProperty("dao").equals("jdbc")) {
            Map<String, String> users = userManager.getAllUsers();
            if (users.containsKey(name) || users.containsValue(hashedPassword)) {
                resp.sendRedirect("/");
            } else {
                userManager.registerUser(name, hashedPassword);
            }
            resp.sendRedirect("/");
        }
    }
}
