package com.carmagazine.catalog;

public class CarStorage {
    private static CarCollection instance;

    private CarStorage() {}

    public static CarCollection getInstance() {
        if (instance == null) {
            instance = new CarCollection();
        }

        return instance;
    }
}
