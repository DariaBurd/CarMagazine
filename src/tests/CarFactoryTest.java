package com.carmagazine.tests;
import catalog.CarFactory;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CarFactoryTest {

    @Test
    @DisplayName("Тест случайной генерации")
    void testGenerateRandom() {
        List<Automobile> cars = CarFactory.generateRandom(3);

        assertEquals(3, cars.size(), "Должно быть 3 машины");

        for (Automobile car : cars) {
            assertNotNull(car.toString());
        }
    }

    @Test
    @DisplayName("Тест загрузки из файла")
    void testLoadFromFile() throws IOException {
        File tempFile = File.createTempFile("cars", ".txt");

        try (PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {
            writer.println("150,2020,Base,Белый");
            writer.println("250,2022,Sport,Красный");
            writer.println("");
            writer.println("плохая,строка,с,ошибкой");
            writer.println("350,2023,Premium,Черный");
        }

        List<Automobile> cars = CarFactory.loadFromFile(tempFile.getAbsolutePath());

        assertEquals(3, cars.size());

        tempFile.delete();
    }

    @Test
    @DisplayName("Тест ручного ввода")
    void testInputFromUser() {
        String input = "150\n2020\nBase\nБелый\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        List<Automobile> cars = CarFactory.inputFromUser(scanner, 1);

        assertEquals(1, cars.size());
    }

    @Test
    @DisplayName("Тест файл не найден")
    void testFileNotFound() {
        List<Automobile> cars = CarFactory.loadFromFile("нет_такого_файла.txt");
        assertTrue(cars.isEmpty());
    }
}
