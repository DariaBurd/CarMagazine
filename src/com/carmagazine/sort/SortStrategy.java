package com.carmagazine.sort;

import com.carmagazine.automobile.Automobile;

import java.util.List;

public interface SortStrategy {
    void sort(List<Automobile> automobiles);
}