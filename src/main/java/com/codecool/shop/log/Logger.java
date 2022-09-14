package com.codecool.shop.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Logger {

    public static void logToFile(Order order) {
        String filename = order.getId() + LocalDate.now() + ".txt";

        try {
            File file = new File(filename);
            if (file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(file);
                // TODO write order attributes to file
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
