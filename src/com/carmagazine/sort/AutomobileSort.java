package com.carmagazine.sort;

import java.util.ArrayList;
import java.util.List;

public class AutomobileSort {
    private SortStrategy strSort;

    public void setStrategy(SortStrategy strategy) {
        this.strSort = strategy;
    }

    public void sort (List<Automobile> auto){
        if (strSort==null) {
            throw new IllegalStateException("Ошибка");
        }
        strSort.sort(auto);
    }

    public void print (List<Automobile> auto, String title){
        System.out.println("\n"+title);
        List<Automobile> autoCopy = new ArrayList<>(auto);
        sort(autoCopy);
        autoCopy.forEach(System.out::println);
        System.out.println("_________________");
    }
}
