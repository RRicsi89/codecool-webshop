package com.codecool.shop.log;

import com.codecool.shop.model.*;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Logger {

    public static void logToFile(Order order, boolean successful) {
        String filename = order.getId() + String.valueOf(LocalDate.now()) + ".json";
        Path filePath = FileSystems.getDefault().getPath("src", "main", "webapp", "logs", filename);

        LogOrder logOrder = createLogOrder(order, successful);

        String logData = new Gson().toJson(logOrder);

        try {
            File file = new File(filePath.toString());
            if (file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(logData);
                fileWriter.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static LogOrder createLogOrder(Order order, boolean paid) {

        LogOrder logOrder = new LogOrder(order.getId(), paid, order.getUser());

        List<Item> items = order.getCart();

        for (Item item: items) {
            Product product = item.product;
            String categories = product.getProductCategory().stream()
                    .map(ProductCategory::getName).collect(Collectors.joining(","));
            LogItem logItem = new LogItem(product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    categories,
                    product.getSupplier().getName(),
                    item.quantity,
                    item.totalPrice);
            logOrder.addItem(logItem);
        }
        return logOrder;
    }
}
