package search;

import automobile.Automobile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KAA
 */
// Конкретная стратегия
// Поиск автомобилей по диапазону мощности
public class SearchByPowerRange implements SearchStrategy {

    @Override
    public List<Automobile> search(List<Automobile> automobiles, String query) {
        List<Automobile> result = new ArrayList<>();

        if (query == null || query.trim().isEmpty()) {
            return result;
        }

        String searchQuery = query.trim();

        // Поиск по диапазону
        if (searchQuery.contains("-")) {
            String[] parts = searchQuery.split("\\s*-\\s*");
            if (parts.length == 2) {
                try {
                    int min = Integer.parseInt(parts[0]);
                    int max = Integer.parseInt(parts[1]);

                    for (Automobile car : automobiles) {
                        if (max > 0 && car.getPower() >= min && car.getPower() <= max) {
                            result.add(car);
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: неверный формат диапазона!");
                    System.out.println("Причина: " + e.getMessage());
//                    e.printStackTrace();
                }
            }
        }
        // Поиск ">"
        else if (searchQuery.startsWith(">")) {
            try {
                int min = Integer.parseInt(searchQuery.substring(1).trim());

                for (Automobile car : automobiles) {
                    if (car.getPower() > min) {
                        result.add(car);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: неверный формат!");
                System.out.println("Причина: " + e.getMessage());
//                e.printStackTrace();
            }
        }
        // Поиск "<"
        else if (searchQuery.startsWith("<")) {
            try {
                int max = Integer.parseInt(searchQuery.substring(1).trim());

                for (Automobile car : automobiles) {
                    if (max > 0 && car.getPower() < max) {
                        result.add(car);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: неверный формат!");
                System.out.println("Причина: " + e.getMessage());
//              e.printStackTrace();
            }
        }
        // Строгий поиск
        else {
            try {
                int power = Integer.parseInt(searchQuery);

                for (Automobile car : automobiles) {
                    if (power > 0 && car.getPower() == power) {
                        result.add(car);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: неверный формат!");
                System.out.println("Причина: " + e.getMessage());
//                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public String getDescription() {
        return "Поиск по мощности (точное совпадение или диапазон, н-р: 100-200, >100, <200)";
    }
}
