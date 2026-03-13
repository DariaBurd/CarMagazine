package com.carmagazine.search;

import com.carmagazine.automobile.Automobile;

import java.util.Scanner;
import java.util.List;

/**
 * @author KAA
 */
public class SearchManager {
    private final SearchContext searchContext;
    private final Scanner scanner;

    public SearchManager(Scanner scanner) {
        this.searchContext = new SearchContext();
        this.scanner = scanner;
    }

    public List<Automobile> performSearch(List<Automobile> cars, int searchType, String query) {
        SearchStrategy strategy = getStrategyByType(searchType);
        searchContext.setStrategy(strategy);
        return searchContext.executeSearch(cars, query);
    }

    private SearchStrategy getStrategyByType(int searchType) {
        switch (searchType) {
            case 1: return new SearchByColor();
            case 2: return new SearchByConfiguration();
            case 3: return new SearchByPowerRange();
            case 4: return new SearchByYear();
            default: return null;
        }
    }
}