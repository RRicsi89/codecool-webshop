package com.codecool.shop.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class LogOrder {

    private final UUID id;
    private final List<LogItem> items;
    private final String status;
    private final User user;

    public LogOrder(UUID id, boolean paid, User user) {
        this.id = id;
        this.user = user;
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
