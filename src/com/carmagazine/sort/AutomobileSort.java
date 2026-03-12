package com.carmagazine.sort;

import com.carmagazine.automobile.Automobile;

import java.util.ArrayList;
import java.util.List;

public class AutomobileSort {
    private SortStrategy strSort;

    public AutomobileSort() {

    }

    public void setStrategy(SortStrategy strategy) {
        this.strSort = strategy;
    }

    public List<Automobile> sort(List<Automobile> autos) {
        if (strSort == null) {
            throw new IllegalStateException("Стратегия сортировки не установлена!");
        }
        if (autos == null) {
            throw new IllegalArgumentException("Список автомобилей не может быть null!");
        }
        if (autos.isEmpty()) {
            System.out.println("Предупреждение: сортировка пустого списка");
            return new ArrayList<>();  // Возвращаем пустой список
        }

        List<Automobile> sortedCopy = new ArrayList<>(autos);
        strSort.sort(sortedCopy);
        return sortedCopy;
    }

    public void print(List<Automobile> autos, String title) {
        System.out.println("\n" + title);
        List<Automobile> sorted = sort(autos);
        sorted.forEach(System.out::println);
        System.out.println("_________________");
    }
}
