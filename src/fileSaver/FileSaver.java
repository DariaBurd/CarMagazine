package fileSaver;

import automobile.Automobile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author KAA
 */
// Класс для сохранения результатов работы программы в файл
public class FileSaver {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public static void saveSortedCars(String fileName, List<Automobile> sortedCars, String sortType) {
        try(FileWriter fileWriter = new FileWriter(fileName, true); // Режим добавления (append) = true
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter)) {

            printWriter.println("Дата сортировки: " + LocalDateTime.now().format(FORMATTER));
            printWriter.println("РЕЗУЛЬТАТ СОРТИРОВКИ");
            printWriter.println("Сортировка по " + sortType);
            printWriter.println();

            if (sortedCars.isEmpty()) {
                printWriter.println("Коллекция пуста.");
            } else {
                for (int i = 0; i < sortedCars.size(); i++) {
                    printWriter.println((i + 1) + ". " + sortedCars.get(i));
                }
            }
            printWriter.println();
            printWriter.println("Всего записей: " + sortedCars.size());
            printWriter.println();
            printWriter.println();

            System.out.println("Отсортированная коллекция сохранена в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении отсортированной коллекции!");
            System.out.println("Причина: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void saveSearchResults(String fileName, List<Automobile> searchResults, String searchType, String query) {
        try(FileWriter fileWriter = new FileWriter(fileName, true); // Режим добавления (append) = true
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter)) {

            printWriter.println();
            printWriter.println("Дата поиска: " + LocalDateTime.now().format(FORMATTER));
            printWriter.println("РЕЗУЛЬТАТЫ ПОИСКА");
            printWriter.println(searchType);
            printWriter.println("Запрос: \"" + query + "\"");
            printWriter.println();

            if (searchResults.isEmpty()) {
                printWriter.println("Ничего не найдено.");
            } else {
                for (int i = 0; i < searchResults.size(); i++) {
                    printWriter.println((i + 1) + ". " + searchResults.get(i));
                }
            }
            printWriter.println();
            printWriter.println("Найдено: " + searchResults.size());
            printWriter.println();
            printWriter.println();

            System.out.println("Результаты поиска сохранены в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении результатов поиска!");
            System.out.println("Причина: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
