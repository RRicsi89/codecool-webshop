package com.codecool.shop.service;

import java.util.Random;

public class PaymentValidator {

    public static boolean validatePayPalAccount(String username, String password) {
        // TODO check for user data
        return new Random().nextInt(100) >= 50;
    }

    public static boolean validateCreditCard(String owner, int cardNumber, String expDate, int cvvCode) {
        boolean result = true;
        if (String.valueOf(cardNumber).length() > 16) result = false;
        return result;
    }
}
