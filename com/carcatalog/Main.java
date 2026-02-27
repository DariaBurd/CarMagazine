package com.carcatalog;

import java.util.*;

public class Main {
    private static List<Automobile> cars = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== МЕНЮ ===");
            System.out.println("1. Случайные автомобили");
            System.out.println("2. Ввести вручную");
            System.out.println("3. Загрузить из файла");
            System.out.println("4. Показать все");
            System.out.println("0. Выход");
            System.out.print("Выберите: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Сколько автомобилей? ");
                    int n1 = scanner.nextInt();
                    scanner.nextLine();
                    cars = CarFactory.generateRandom(n1);
                    break;

                case 2:
                    System.out.print("Сколько автомобилей? ");
                    int n2 = scanner.nextInt();
                    scanner.nextLine();
                    cars = CarFactory.inputFromUser(scanner, n2);
                    break;

                case 3:
                    System.out.print("Имя файла: ");
                    String file = scanner.nextLine();
                    cars = CarFactory.loadFromFile(file);
                    break;

                case 4:
                    if (cars.isEmpty()) {
                        System.out.println("Список пуст");
                    } else {
                        for (int i = 0; i < cars.size(); i++) {
                            System.out.println((i + 1) + ". " + cars.get(i));
                        }
                    }
                    break;

                case 0:
                    System.out.println("Пока!");
                    return;
            }
        }
    }
}