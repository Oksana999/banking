package org.banking.banking.utils;

public class BankingUtils {
    public static boolean isValidPhone(String phone) {
        return phone.matches("[0-9]+");
    }
}
