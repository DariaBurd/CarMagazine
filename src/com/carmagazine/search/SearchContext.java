package com.carmagazine.search;

import com.carmagazine.automobile.Automobile;

import java.util.List;

/**
 * @author KAA
 */
// Контекст
public class SearchContext {
    private SearchStrategy strategy;

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Automobile> executeSearch(List<Automobile> cars, String query) {
        if (strategy == null) {
            throw new IllegalStateException("Тип поиска не выбран!");
        }
        return strategy.search(cars, query);
    }
}
