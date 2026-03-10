package search;

import automobile.Automobile;
import fileSaver.FileSaver;

import java.util.List;
import java.util.Scanner;

/**
 * @author KAA
 */
public class MainSearch {
    private final SearchManager searchManager;
    private final Scanner scanner;

    public MainSearch(Scanner scanner) {
        this.searchManager = new SearchManager();
        this.scanner = scanner;
    }

    public boolean searchData(List<Automobile> cars) {
        if (cars.isEmpty()) {
            System.out.println("Список автомобилей пуст. Сначала добавьте данные!");
            return true;
        }
        // Бесконечный цикл поиска (пока пользователь не выберет выход)
        while (true) {
            searchManager.printMenu();

            System.out.println("Выберите тип поиска: ");
            int choice = readInt();

            if (!searchManager.isValidChoice(choice)) {
                System.out.println("Неверный выбор. Пожалуйста, выберите 0-4.");
                return true;
            }

            if (choice == 0) {
                System.out.println("Возврат в главное меню.");
                return true;
            }

            // Получаем выбранную стратегию
            SearchStrategy strategy = searchManager.getStrategy(choice);
            System.out.println("\nВыбрано: " + strategy.getDescription());

            System.out.println("Введите поисковый запрос: ");
            String query = scanner.nextLine();

            // Выполняем поиск
            List<Automobile> results = strategy.search(cars, query);

            if (results.isEmpty()) {
                System.out.println("\nНичего не найдено.");
            } else {
                System.out.println("\nНайдено: " + results.size());
                for (int i = 0; i < results.size(); i++) {
                    System.out.println((i + 1) + ". " + results.get(i));
                }
            }

            // Сохранение в файл
            System.out.print("\nСохранить результаты поиска в файл? (да/нет): ");
            String saving = scanner.nextLine().trim().toLowerCase();
            if (saving.equals("да") || saving.equals("yes")) {
                FileSaver.saveSearchResults("search_results.txt", results, strategy.getDescription(), query);
            }

            System.out.println("\nХотите выполнить еще один поиск? (да/нет): ");
            String again = scanner.nextLine().trim().toLowerCase();
            if (!again.equals("да") && !again.equals("yes")) {
                System.out.println("Возврат в главное меню.");
                return true;
            }
        }
    }

    // Безопасное чтение числа
    private int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: неверный формат!");
            System.out.println("Причина: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
}
