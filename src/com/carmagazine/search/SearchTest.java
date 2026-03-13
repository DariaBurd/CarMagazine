package com.carmagazine.search;

import com.carmagazine.automobile.Automobile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KAA
 */
public class SearchTest {
    private List<Automobile> testCars;

    public static void main(String[] args) {
        SearchTest tester = new SearchTest();
        tester.runAllTests();
    }

    public void runAllTests() {
        System.out.println("ЗАПУСК ТЕСТОВ ПОИСКА\n");

        // Тестовые данные
        prepareTestData();

        testSearchByPowerRanger();
        testSearchByYear();
        testSearchByModel();
        testSearchByColor();
        testSearchWithInvalidQuery();
        testSearchWithEmptyQuery();
        testSearchWithNullQuery();
    }

    private void prepareTestData() {
        testCars = new ArrayList<>();

        try {
            testCars.add(new Automobile.Builder()
                    .setPower(150)
                    .setYear(2000)
                    .setConfiguration("Base")
                    .setColor("Желтый")
                    .build());
            testCars.add(new Automobile.Builder()
                    .setPower(200)
                    .setYear(2020)
                    .setConfiguration("Sport")
                    .setColor("Красный")
                    .build());
            testCars.add(new Automobile.Builder()
                    .setPower(250)
                    .setYear(2022)
                    .setConfiguration("Luxury")
                    .setColor("Черный")
                    .build());
            testCars.add(new Automobile.Builder()
                    .setPower(120)
                    .setYear(2000)
                    .setConfiguration("Base")
                    .setColor("Зеленый")
                    .build());
            testCars.add(new Automobile.Builder()
                    .setPower(280)
                    .setYear(2024)
                    .setConfiguration("Sport")
                    .setColor("Желтый")
                    .build());

            System.out.println("Подготовлено 5 тестовых автомобилей:");
            for (int i = 0; i < testCars.size(); i++) {
                System.out.println((i + 1) + ". " + testCars.get(i));
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Ошибка при подготовке тестовых автомобилей!");
            System.out.println("Причина: " + e.getMessage());
        }
    }

    private void testSearchByPowerRanger() {
        System.out.println("ТЕСТ: Поиск по мощности");

        SearchStrategy strategy = new SearchByPowerRange();

        List<Automobile> result1 = strategy.search(testCars, "100-200");
        boolean testPassed1 = (result1.size() == 3);
        printTestResult("Диапазон \"100-200\":", testPassed1, 3, result1.size());

        List<Automobile> result2 = strategy.search(testCars, ">250");
        boolean testPassed2 = (result2.size() == 1);
        printTestResult("\">250\":", testPassed2, 1, result2.size());

        List<Automobile> result3 = strategy.search(testCars, "<200");
        boolean testPassed3 = (result3.size() == 2);
        printTestResult("\"<200\":", testPassed3, 2, result3.size());

        List<Automobile> result4 = strategy.search(testCars, "280");
        boolean testPassed4 = (result4.size() == 1);
        printTestResult("\"280\":", testPassed4, 1, result4.size());

        List<Automobile> result5 = strategy.search(testCars, " 100 - 200 ");
        boolean testPassed5 = (result5.size() == 3);
        printTestResult("Диапазон \" 100 - 200 \" (с пробелами):", testPassed5, 3, result5.size());

        List<Automobile> result6 = strategy.search(testCars, " > 250 ");
        boolean testPassed6 = (result6.size() == 1);
        printTestResult("\" > 250 \" (с пробелами):", testPassed6, 1, result6.size());

        List<Automobile> result7 = strategy.search(testCars, " < 200 ");
        boolean testPassed7 = (result7.size() == 2);
        printTestResult("\" < 200 \" (с пробелами):", testPassed7, 2, result7.size());

        List<Automobile> result8 = strategy.search(testCars, " 280 ");
        boolean testPassed8 = (result8.size() == 1);
        printTestResult("\" 280 \" (с пробелами):", testPassed8, 1, result8.size());

        System.out.println();
    }

    private void testSearchByYear() {
        System.out.println("ТЕСТ: Поиск по году выпуска");

        SearchStrategy strategy = new SearchByYear();

        List<Automobile> result1 = strategy.search(testCars, "2000");
        boolean testPassed1 = (result1.size() == 2);
        printTestResult("\"2000\":", testPassed1, 2, result1.size());

        List<Automobile> result2 = strategy.search(testCars, "1996");
        boolean testPassed2 = (result2.size() == 0);
        printTestResult("\"1996\" (нет в списке):", testPassed2, 0, result2.size());

        System.out.println();
    }

    private void testSearchByModel() {
        System.out.println("ТЕСТ: Поиск по комплектации");

        SearchStrategy strategy = new SearchByConfiguration();

        List<Automobile> result1 = strategy.search(testCars, "Base");
        boolean testPassed1 = (result1.size() == 2);
        printTestResult("\"Base\":", testPassed1, 2, result1.size());

        List<Automobile> result2 = strategy.search(testCars, "Premium");
        boolean testPassed2 = (result2.size() == 0);
        printTestResult("\"Premium\" (нет в списке):", testPassed2, 0, result2.size());

        List<Automobile> result3 = strategy.search(testCars, "spor");
        boolean testPassed3 = (result3.size() == 2);
        printTestResult("\"spor\" (нижний регистр, часть слова):", testPassed3, 2, result3.size());

        System.out.println();
    }

    private void testSearchByColor() {
        System.out.println("ТЕСТ: Поиск по цвету");

        SearchStrategy strategy = new SearchByColor();

        List<Automobile> result1 = strategy.search(testCars, "Черный");
        boolean testPassed1 = (result1.size() == 1);
        printTestResult("\"Черный\":", testPassed1, 1, result1.size());

        List<Automobile> result2 = strategy.search(testCars, "Оранжевый");
        boolean testPassed2 = (result2.size() == 0);
        printTestResult("\"Оранжевый\" (нет в списке):", testPassed2, 0, result2.size());

        List<Automobile> result3 = strategy.search(testCars, "жел");
        boolean testPassed3 = (result3.size() == 2);
        printTestResult("\"жел\" (нижний регистр, часть слова):", testPassed3, 2, result3.size());

        System.out.println();
    }

    private void testSearchWithInvalidQuery() {
        System.out.println("ТЕСТ: Некорректный запрос");

        SearchStrategy strategy1 = new SearchByPowerRange();
        SearchStrategy strategy2 = new SearchByYear();

        try {
            List<Automobile> result = strategy1.search(testCars, "100-двести");
            boolean testPassed = (result.size() == 0);
            printTestResult("Мощность \"100-двести\":", testPassed, 0, result.size());
        } catch (Exception e) {
            printTestResult("Мощность \"100-двести\" (возникло исключение):", false, 0, -1);
        }
        System.out.println("-----------------------");

        try {
            List<Automobile> result = strategy1.search(testCars, ">=200");
            boolean testPassed = (result.size() == 0);
            printTestResult("Мощность \">=200\":", testPassed, 0, result.size());
        } catch (Exception e) {
            printTestResult("Мощность \">=200\" (возникло исключение):", false, 0, -1);
        }
        System.out.println("-----------------------");

        try {
            List<Automobile> result = strategy2.search(testCars, "тысяча...");
            boolean testPassed = (result.size() == 0);
            printTestResult("Год выпуска \"тысяча...\":", testPassed, 0, result.size());
        } catch (Exception e) {
            printTestResult("Год выпуска \"тысяча...\" (возникло исключение):", false, 0, -1);
        }

        System.out.println();
    }

    private void testSearchWithEmptyQuery() {
        System.out.println("ТЕСТ: Пустой поисковый запрос");

        SearchStrategy strategy1 = new SearchByPowerRange();
        SearchStrategy strategy2 = new SearchByYear();
        SearchStrategy strategy3 = new SearchByConfiguration();
        SearchStrategy strategy4 = new SearchByColor();

        List<Automobile> result1 = strategy1.search(testCars, "");
        boolean testPassed1 = (result1.size() == 0);
        printTestResult("Мощность \"\" (пустой запрос):", testPassed1, 0, result1.size());

        List<Automobile> result2 = strategy2.search(testCars, " ");
        boolean testPassed2 = (result2.size() == 0);
        printTestResult("Год выпуска \" \" (запрос с пробелом):", testPassed2, 0, result2.size());

        List<Automobile> result3 = strategy3.search(testCars, "");
        boolean testPassed3 = (result3.size() == 0);
        printTestResult("Комплектация \"\" (пустой запрос):", testPassed3, 0, result3.size());

        List<Automobile> result4 = strategy4.search(testCars, "  ");
        boolean testPassed4 = (result4.size() == 0);
        printTestResult("Цвет \"  \" (запрос с пробелами):", testPassed4, 0, result4.size());

        System.out.println();
    }

    private void testSearchWithNullQuery() {
        System.out.println("ТЕСТ: Null поисковый запрос");

        SearchStrategy strategy1 = new SearchByPowerRange();
        SearchStrategy strategy2 = new SearchByYear();
        SearchStrategy strategy3 = new SearchByConfiguration();
        SearchStrategy strategy4 = new SearchByColor();

        List<Automobile> result1 = strategy1.search(testCars, null);
        boolean testPassed1 = (result1.size() == 0);
        printTestResult("Мощность \"Null\" (Null запрос):", testPassed1, 0, result1.size());

        List<Automobile> result2 = strategy2.search(testCars, null);
        boolean testPassed2 = (result2.size() == 0);
        printTestResult("Год выпуска \"Null\" (Null запрос):", testPassed2, 0, result2.size());

        List<Automobile> result3 = strategy3.search(testCars, null);
        boolean testPassed3 = (result3.size() == 0);
        printTestResult("Комплектация \"Null\" (Null запрос):", testPassed3, 0, result3.size());

        List<Automobile> result4 = strategy4.search(testCars, null);
        boolean testPassed4 = (result4.size() == 0);
        printTestResult("Цвет \"Null\" (Null запрос):", testPassed4, 0, result4.size());
    }

    private void printTestResult(String testName, boolean passed, int expectedSize, int actualSize) {
        if (passed) {
            System.out.printf("%s (ожидается: %d) - OK (получено: %d)%n", testName, expectedSize, actualSize);
        } else {
            System.out.printf("%s - ОШИБКА! Ожидалось: %d, получено: %d.%n", testName, expectedSize, actualSize);
        }
    }
}