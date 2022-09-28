package com.codecool.shop.dao;

import javax.sql.DataSource;

import com.codecool.shop.config.ConfigParser;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {

    public static DataSource connect() {
        try {
            Properties connectionProps = ConfigParser.parseConfigFile("connection.properties");
            PGSimpleDataSource dataSource = new PGSimpleDataSource();
            dataSource.setDatabaseName(connectionProps.getProperty("database"));
            dataSource.setUser(connectionProps.getProperty("user"));
            dataSource.setPassword(connectionProps.getProperty("password"));

            dataSource.getConnection().close();
            return dataSource;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setup() {
        DataSource dataSource = connect();
        // TODO implement jdbc daos
    }

    public void run() {
        setup();
    }
}
