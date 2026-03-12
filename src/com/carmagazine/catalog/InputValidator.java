package com.carmagazine.catalog;

public class InputValidator {

    public static boolean isValidPower(String powerStr) {
        try {
            int power = Integer.parseInt(powerStr);
            return power > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidYear(String yearStr) {
        try {
            int year = Integer.parseInt(yearStr);
            return year >= 1886 && year <= 2026;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidConfiguration(String config) {
        return config != null && !config.trim().isEmpty();
    }

    public static boolean isValidColor(String color) {
        return color != null && !color.trim().isEmpty();
    }
}