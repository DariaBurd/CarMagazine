package com.carmagazine.search;

import com.carmagazine.automobile.Automobile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KAA
 */
// Конкретная стратегия
// Поиск автомобилей по году выпуска
public class SearchByYear implements SearchStrategy {
    @Override
    public List<Automobile> search(List<Automobile> automobiles, String query) {
        List<Automobile> result = new ArrayList<>();

        if (query == null || query.trim().isEmpty()) {
            return result;
        }

        try {
            int year = Integer.parseInt(query.trim());

            for (Automobile car : automobiles) {
                if (car.getYear() == year) {
                    result.add(car);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: неверный формат!");
            System.out.println("Причина: " + e.getMessage());
//            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String getDescription() {
        return "Поиск по году (точное совпадение)";
    }
}
