package search;

import automobile.Automobile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KAA
 */
// Конкретная стратегия
// Поиск автомобилей по комплектации
public class SearchByConfiguration implements SearchStrategy {
    @Override
    public List<Automobile> search(List<Automobile> automobiles, String query) {
        List<Automobile> result = new ArrayList<>();

        if (query == null || query.trim().isEmpty()) {
            return result;
        }

        String searchQuery = query.trim().toLowerCase();

        for (Automobile car : automobiles) {
            if (car.getConfiguration().toLowerCase().contains(searchQuery)) {
                result.add(car);
            }
        }

        return result;
    }

    @Override
    public String getDescription() {
        return "Поиск по комплектации (частичное совпадение)";
    }
}
