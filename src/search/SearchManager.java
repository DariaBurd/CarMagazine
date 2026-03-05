package search;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author KAA
 */
// Контекст
public class SearchManager {
    private final Map<Integer, SearchStrategy> strategies = new LinkedHashMap<>();

    public SearchManager() {
        // Регистрируем все стратегии поиска
        strategies.put(1, new SearchByPowerRange());
        strategies.put(2, new SearchByYear());
        strategies.put(3, new SearchByConfiguration());
        strategies.put(4, new SearchByColor());
    }

    public void printMenu() {
        System.out.println("\n=== Поиск автомобилей ===");
        for (Map.Entry<Integer, SearchStrategy> entry : strategies.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getDescription());
        }
        System.out.println("0. Назад в главное меню");
    }

    public SearchStrategy getStrategy(int choice) {
        return strategies.get(choice);
    }

    public boolean isValidChoice(int choice) {
        return choice >= 0 && choice <= strategies.size();
    }
}
