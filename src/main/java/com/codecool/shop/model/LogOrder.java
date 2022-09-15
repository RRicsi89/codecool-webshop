package com.codecool.shop.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class LogOrder {

    private final UUID id;
    private final List<LogItem> items;
    private final String status;

    public LogOrder(UUID id, boolean paid) {
        this.id = id;
        items = new LinkedList<>();
        if (paid) {
            this.status = "Successful";
        } else {
            this.status = "Cancelled";
        }
    }

    public void addItem(LogItem item) {
        this.items.add(item);
    }
}
