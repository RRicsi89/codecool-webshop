package com.codecool.shop.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigParser {

    public static Properties parseConfigFile(String fileName) {
        try {
            Path filePath = Paths.get("src", "main", "resources", fileName);
            Properties connectionProps = new Properties();
            connectionProps.load(new FileInputStream(filePath.toString()));
            return connectionProps;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
