package com.carmagazine.test;
package com.carmagazine.sort.AutomobileSort;

public class Test {
    public static void main(String[] args) {
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

        System.out.println("Исходный список авто:");
        testList.forEach(System.out::println);
        System.out.println("_________________");


        AutomobileSort sorter = new AutomobileSort();

        sorter.setStrategy(new SortByPower());
        sorter.print(testList, "Cортировка по мощности:");

        sorter.setStrategy(new SortByYear());
        sorter.print(testList, "Сортировка по году:");

        sorter.setStrategy(new SortByConfiguration());
        sorter.print(testList, "Сортировка по комплектации:");

        sorter.setStrategy(new SortByColor());
        sorter.print(testList, "Сортировка по цвету:");

        sorter.setStrategy(new AdditionSortByColor());
        sorter.print(testList, "доп задание: сортировка чётные годы сортируются по возрастанию, нечётные остаются на местах");

    }}
