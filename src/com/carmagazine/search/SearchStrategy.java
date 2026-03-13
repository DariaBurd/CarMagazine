package com.carmagazine.search;

import com.carmagazine.automobile.Automobile;

import java.util.List;

/**
 * @author KAA
 */
// Интерфейс Стратегии
public interface SearchStrategy {
    List<Automobile> search(List<Automobile> automobiles, String query);

    String getDescription();
}
