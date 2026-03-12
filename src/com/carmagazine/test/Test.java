package com.carmagazine.test;

import com.carmagazine.automobile.Automobile;
import com.carmagazine.sort.*;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        List<Automobile> cars = createTestCars();
        showOriginal(cars);
        runAll(cars);
    }

    public static List<Automobile> createTestCars() {
        List<Automobile> testList = new ArrayList<>();

        testList.add(new Automobile.Builder().setPower(180).setYear(2001).setConfiguration("Comfort").setColor("Синий").build());
        testList.add(new Automobile.Builder().setPower(250).setYear(2013).setConfiguration("Prestige").setColor("Чёрный").build());
        testList.add(new Automobile.Builder().setPower(120).setYear(2016).setConfiguration("Base").setColor("Красный").build());
        testList.add(new Automobile.Builder().setPower(200).setYear(2018).setConfiguration("Limited").setColor("Белый").build());
        testList.add(new Automobile.Builder().setPower(180).setYear(2018).setConfiguration("Luxury").setColor("Зелёный").build());
        testList.add(new Automobile.Builder().setPower(150).setYear(2013).setConfiguration("Base").setColor("Серебристый").build());
        testList.add(new Automobile.Builder().setPower(320).setYear(2025).setConfiguration("Sport").setColor("Красный").build());
        testList.add(new Automobile.Builder().setPower(190).setYear(2024).setConfiguration("Comfort").setColor("Чёрный").build());
        testList.add(new Automobile.Builder().setPower(110).setYear(2009).setConfiguration("Classic").setColor("Белый").build());
        testList.add(new Automobile.Builder().setPower(280).setYear(2018).setConfiguration("Premium").setColor("Синий").build());
        testList.add(new Automobile.Builder().setPower(200).setYear(2026).setConfiguration("Luxury").setColor("Зелёный").build());
        testList.add(new Automobile.Builder().setPower(240).setYear(2019).setConfiguration("Elite").setColor("Серый").build());
        testList.add(new Automobile.Builder().setPower(170).setYear(2016).setConfiguration("Standard").setColor("Жёлтый").build());
        testList.add(new Automobile.Builder().setPower(300).setYear(2023).setConfiguration("Standard").setColor("Оранжевый").build());
        testList.add(new Automobile.Builder().setPower(130).setYear(2014).setConfiguration("Premium").setColor("Коричневый").build());
        testList.add(new Automobile.Builder().setPower(260).setYear(2014).setConfiguration("Sport").setColor("Фиолетовый").build());
        testList.add(new Automobile.Builder().setPower(210).setYear(2023).setConfiguration("Base").setColor("Металлик").build());

        return testList;
    }

    public static void showOriginal(List<Automobile> cars) {
        System.out.println("Исходный список авто:");
        cars.forEach(System.out::println);
        System.out.println("__________________");
    }

    public static void runAll(List<Automobile> cars) {
        AutomobileSort sorter = new AutomobileSort();

        runSort(sorter, cars, new SortByPower(),       "Сортировка по мощности");
        runSort(sorter, cars, new SortByYear(),        "Сортировка по году");
        runSort(sorter, cars, new SortByConfiguration(),"Сортировка по комплектации");
        runSort(sorter, cars, new SortByColor(),       "Сортировка по цвету");
        runSort(sorter, cars, new AdditionSortByYear(), "Доп. задание: чётные годы по возрастанию");
    }

    public static void runSort(AutomobileSort sorter, List<Automobile> cars, SortStrategy strategy, String title) {
        sorter.setStrategy(strategy);
        sorter.print(cars, title + ":");
        System.out.println();

    }
}
