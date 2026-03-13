/*
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                    Version 2, December 2004
 *
 * Copyright (C) 2004 Sam Hocevar <sam@hocevar.net>
 *
 * Everyone is permitted to copy and distribute verbatim or modified
 * copies of this license document, and changing it is allowed as long
 * as the name is changed.
 *
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 *   0. You just DO WHAT THE FUCK YOU WANT TO.
 *
 * ----------------------------------------------------------------------
 * Copyright (C) 2026 fnJey
 * ----------------------------------------------------------------------
 */

package com.carmagazine.menu;

import com.carmagazine.automobile.Automobile;
import com.carmagazine.automobile.AutomobileTest;
import com.carmagazine.catalog.CarCollection;
import com.carmagazine.catalog.CarStorage;
import com.carmagazine.fileSaver.FileSaver;
import com.carmagazine.search.*;
import com.carmagazine.sort.AutomobileSort;
import com.carmagazine.sort.SortStrategy;
import com.carmagazine.thread.ThreadManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.carmagazine.automobile.AutomobileTest.*;
import static com.carmagazine.catalog.CarFactory.*;
import static com.carmagazine.menu.MenuManager.*;
import static com.carmagazine.test.Test.*;

import com.carmagazine.search.SearchTest;

public class MenuPrinter {
    private CarCollection carStorage;
    private Scanner scanner;
    private ThreadManager threadManager;
    private SearchManager searchManager;

    public MenuPrinter(Scanner scanner, ThreadManager threadManager) {
        this.carStorage = CarStorage.getInstance();
        this.scanner = scanner;
        this.threadManager = threadManager;
        this.searchManager = new SearchManager(scanner);
    }


    public void printMainMenu() {
        printTableHeader();
        printMenuItem(1, "Исходник", "Выбор способа загрузки авто");
        printMenuItem(2, "Поиск", "Найти авто из исходника");
        printMenuItem(3, "Сортировка", "Сортировка автомобилей");
        printThread(4);
        printMenuItem(5, "Тест", "Запустить все тесты программы");
        printMenuItem(6, "База", "Выгрузить текущую базу автомобилей");
        printMenuItem(7, "Принтер", "Отобразить текущую базу автомобилей");
        printMenuItem(0, "Выход", "Завершить программу");
        printTableFooter();
        printPrompt("[0/1/2/3/4/5/6/7]");
    }

    public void printSourceMenu() {
        printTableHeader();
        printMenuItem(1, "Файл", "Загрузить авто из .txt");
        printMenuItem(2, "Ручной ввод", "Описать авто вручную");
        printMenuItem(3, "Генерация", "Создание рандомных авто");
        printMenuItem(0, "Назад", "Вернуться в главное меню");
        printTableFooter();
        printPrompt("[0/1/2/3]");
    }

    public void printSearchMenu() {
        printTableHeader();
        printMenuItem(1, "Цвет", "Поиск по цвету");
        printMenuItem(2, "Конфигурация", "Поиск по конфигурации");
        printMenuItem(3, "Мощность", "Поиск по мощности");
        printMenuItem(4, "Год", "Поиск по году");
        printMenuItem(0, "Назад", "Вернуться в главное меню");
        printTableFooter();
        printPrompt("[0/1/2/3/4]");
    }

    public void printSortMenu() {
        printTableHeader();
        printMenuItem(1, "Цвет", "Сортировка по цвету");
        printMenuItem(2, "Конфигурация", "Сортировка по конфигурации");
        printMenuItem(3, "Мощность", "Сортировка по мощности");
        printMenuItem(4, "Год", "Сортировка по году");
        printMenuItem(5, "Доп. задание", "Сортировка чётные годы сортируются по возрастанию");
        printMenuItem(0, "Назад", "Вернуться в главное меню");
        printTableFooter();
        printPrompt("[0/1/2/3/4/5]");
    }

    private void executeSearchMenu(String promptMessage, int searchType, String reportTitle, String description) {
        Runnable task = () -> {
            List<Automobile> cars = carStorage.asList();

            if (cars.isEmpty()) {
                System.out.println("Автомобили не найдены!!!");
                return;
            }

            System.out.print(promptMessage);
            String query = scanner.nextLine();

            List<Automobile> result = searchManager.performSearch(cars, searchType, query);

            if (result.isEmpty()) System.out.printf("Автомобили по запросу %s не найдены\n", query);
            else {
                System.out.printf("Найдено: %d автомобилей\n", result.size());
                for (int i = 0; i < result.size(); i++) {
                    System.out.println((i + 1) + ". " + result.get(i));
                }
            }

            if (!result.isEmpty()) {
                System.out.print("\nСохранить результат поиска в файл? (y/n): ");
                String answer = scanner.nextLine();
                if (answer.trim().equalsIgnoreCase("y")) {
                    System.out.print("Введите путь файла .txt для сохранения выполненного поиска : ");
                    String filePath = scanner.nextLine();

                    if (!FileSaver.isValidFilePath(filePath)) {
                        return;
                    }

                    FileSaver.saveSearchResults(filePath, result, reportTitle, description + query);
                }
            }
        };
        threadManager.execute(task);
    }

    public void printSearchByColorMenu() {
        executeSearchMenu("Введите цвет для поиска: ", 1, "Поиск по цвету ", "Цвет: ");
    }

    public void printSearchByComplectationMenu() {
        executeSearchMenu("Введите комплектацию для поиска: ", 2, "Поиск по комплектации ", "Комплектация: ");
    }

    public void printSearchByYearMenu() {
        executeSearchMenu("Введите год для поиска: ", 4, "Поиск по году ", "Год: ");
    }

    public void printSearchByPowerMenu() {
        executeSearchMenu("Введите мощность для поиска: ", 3, "Поиск по мощности ", "мощность: ");
    }


    public void printSortByMenu(SortStrategy strategy, String sortType) {
        Runnable task = () -> {
            List<Automobile> cars = new ArrayList<>(carStorage.asList());
            if (cars.isEmpty()) {
                System.out.println("Автомобили в базе не найдены!!!");
                return;
            }
            AutomobileSort sorting = new AutomobileSort();
            sorting.setStrategy(strategy);

            List<Automobile> result = sorting.sort(cars);
            System.out.println("Сортировка завершена!");

            System.out.print("Сохранить результат поиска в файл? (y/n): ");
            String answer = scanner.nextLine();
            if (answer.trim().equalsIgnoreCase("y")) {
                System.out.print("Введите путь файла .txt для сохранения выполненного поиска : ");
                String filePath = scanner.nextLine();

                if (!FileSaver.isValidFilePath(filePath)) {
                    return;
                }

                FileSaver.saveSortedCars(filePath, result, sortType);
            }

        };
        threadManager.execute(task);
    }

    public void printFileLoadMenu() {
        System.out.print("Введите путь к файлу .txt: ");
        String filePath = scanner.nextLine();

        if (filePath.trim().isEmpty()) {
            System.out.println("Ошибка: путь к файлу не может быть пустым!");
            return;
        }
        if (!filePath.toLowerCase().endsWith(".txt")) {
            System.out.println("Ошибка: файл должен быть с расширением .txt!");
            return;
        }

        Runnable task = () -> {
            var loadedCars = loadFromFile(filePath);
            if (loadedCars.isEmpty()) {
                System.out.println("Не удалось загрузить автомобили из файла!");
                return;
            }
            carStorage.addAll(loadedCars);
            System.out.println("Загружено " + loadedCars.size() + " автомобилей!");
        };
        threadManager.execute(task);
    }

    public void printManualMenu() {
        Runnable task = () -> {
            System.out.print("Введите количество авто для ручного ввода: ");
            String countStr = scanner.nextLine();
            try {
                int count = Integer.parseInt(countStr);
                if (count <= 0) {
                    System.out.println("Ошибка: количество должно быть положительным числом!");
                    return;
                }
                var newCars = inputFromUser(scanner, count);
                carStorage.addAll(newCars);
                System.out.println("Добавлено " + newCars.size() + " автомобилей!");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число!");
            }
        };
        threadManager.execute(task);
    }

    public void printGenerateMenu() {
        Runnable task = () -> {
            System.out.print("Введите количество авто для генерации: ");
            String countStr = scanner.nextLine();
            try {
                int count = Integer.parseInt(countStr);
                if (count <= 0) {
                    System.out.println("Ошибка: количество должно быть положительным числом!");
                    return;
                }
                var generatedCars = generateRandom(count);
                carStorage.addAll(generatedCars);
                System.out.println("Добавлено " + generatedCars.size() + " автомобилей!");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число!");
            }
        };
        threadManager.execute(task);
    }

    public boolean printAllTests() {
        try {
            System.out.println("[ТЕСТ]: Запуск через 3 секунды");
            Thread.sleep(3000);

            System.out.println("[ТЕСТ]: ЗАПУСК ТЕСТА ПРОВЕРКИ АВТОМОБИЛЯ");
            testValidCar();
            testInvalidPower();
            testInvalidYear();
            testEmptyConfiguration();
            testMissingField();

            SearchTest tester = new SearchTest();
            System.out.print("[ТЕСТ]: ");
            tester.runAllTests();

            System.out.println("[ТЕСТ]: ЗАПУСК ТЕСТОВ СОРТИРОВКИ");
            List<Automobile> cars = createTestCars();
            runAll(cars);

            return true;
        } catch (Exception e) {
            System.out.println("Ошибка запуска теста: " + e.getMessage());
            return false;
        }
    }

    public boolean printPrinterOut() {
        try {
            List<Automobile> cars = new ArrayList<>(carStorage.asList());
            if (cars.isEmpty()) {
                System.out.println("Автомобили не найдены!!!");
                return false;
            }
            cars.forEach(System.out::println);
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка вывода: " + e.getMessage());
            return false;
        }
    }

    public boolean printExportBase() {
        try {
            List<Automobile> cars = new ArrayList<>(carStorage.asList());
            if (cars.isEmpty()) {
                System.out.println("Автомобили не найдены!!!");
                return false;
            }
            System.out.print("Введите путь для сохранения выполненного поиска : ");
            String filePath = scanner.nextLine();

            if (!FileSaver.isValidFilePath(filePath)) {
                return false;
            }

            FileSaver.saveCurrentCars(filePath, cars);
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка выгрузки базы: " + e.getMessage());
            return false;
        }
    }

    public void printHeader() {
        System.out.println(ANSI_BLUE + "                          ╔═══════════════════" + ANSI_RESET + ANSI_PURPLE +
                " CarMagazine v1.0.2 " + ANSI_RESET + ANSI_BLUE +
                "═══════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "                          ║                                                          ║" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "                          ║  ____ ____ ____    _  _ ____ ____ ____ ___  _ _  _ ____  ║" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "                          ║  |    |__| |__/    |\\/| |__| | __ |__|   /  | |\\ | |___  ║" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "                          ║  |___ |  | |  \\    |  | |  | |__] |  |  /__ | | \\| |___  ║" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "                          ║                                                          ║" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "                          ╚══════════════════════════════════════════════════════════╝" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "                               Github: https://github.com/DariaBurd/CarMagazine/" + ANSI_RESET);
    }

    private void printTableHeader() {
        int cars = carStorage.size();
        System.out.println(ANSI_PURPLE + "                               Текущее количество авто в программе: " + cars + ANSI_RESET);
        System.out.println("+──────────────┬───────────────────────────────────┬───────────────────────────────────────────────────────────+");
        System.out.println("│      №       │ Действие                          │ Описание                                                  │");
        System.out.println("├──────────────┼───────────────────────────────────┼───────────────────────────────────────────────────────────┤");
    }

    private void printMenuItem(int number, String action, String description) {
        System.out.printf("│      " + ANSI_CYAN + "%d" + ANSI_RESET + "       │ " + ANSI_YELLOW + "%-33s" + ANSI_RESET + " │ " + ANSI_WHITE + "%-57s" + ANSI_RESET + " │\n", number, action, description);
    }

    private void printTableFooter() {
        System.out.println("+──────────────┴───────────────────────────────────┴───────────────────────────────────────────────────────────+");
    }

    private void printPrompt(String options) {
        System.out.print(ANSI_PURPLE + ">" + ANSI_RESET + " Выберите действие " + ANSI_GREEN + options + ": " + ANSI_RESET);
    }

    private void printThread(int number) {
        String threadStatus = threadManager.isEnabled() ? "ВКЛ" : "ВЫКЛ";
        String threadColor = threadManager.isEnabled() ? ANSI_GREEN : ANSI_RED;

        String descriptionText = "Режим: " + threadColor + threadStatus;

        System.out.printf("│      " + ANSI_CYAN + "%d" + ANSI_RESET + "       │ " + ANSI_YELLOW + "%-33s" + ANSI_RESET + " │ " + ANSI_WHITE + "%-57s" + ANSI_RESET + "      │\n",
                number, "Многопоточность", descriptionText);
    }
}