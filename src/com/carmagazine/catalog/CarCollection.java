package com.carmagazine.catalog;
import java.util.*;
import java.util.stream.Stream;

/**
 * Кастомная коллекция для автомобилей.
 * Реализует AbstractCollection
 * Для сортировки использую стратегии второго разработчика.
 */
public class CarCollection extends AbstractCollection<Automobile> {
    private final List<Automobile> cars = new ArrayList<>();

    @Override
    public Iterator<Automobile> iterator() {
        return cars.iterator();
    }

    @Override
    public int size() {
        return cars.size();
    }


    @Override
    public boolean add(Automobile car) {
        return cars.add(car);
    }


     // Добавить все элементы из другой коллекции

    public boolean addAll(Collection<? extends Automobile> collection) {
        return cars.addAll(collection);
    }

    // Сортировка через второго разработчика
    public void sortWith(SortStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Стратегия сортировки не может быть null");
        }
        strategy.sort(cars);
        System.out.println("Коллекция отсортирована с помощью " + strategy.getClass().getSimpleName());
    }

    // Получить внутренний список

    public List<Automobile> asList() {
        return cars;
    }

    // Очистить коллекцию

    public void clear() {
        cars.clear();
    }

    // Проверить, содержит ли коллекция автомобиль с указанными характеристиками

    public boolean contains(Automobile car) {
        return cars.contains(car);
    }

    //Получить автомобиль по индексу

    public Automobile get(int index) {
        if (index < 0 || index >= cars.size()) {
            throw new IndexOutOfBoundsException("Индекс за пределами: " + index);
        }
        return cars.get(index);
    }

    //  Получить стрим элементов коллекции
    public Stream<Automobile> stream() {
        return cars.stream();
    }

    //Получить параллельный стрим
    public Stream<Automobile> parallelStream() {
        return cars.parallelStream();
    }

    // ========== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ==========

    @Override
    public String toString() {
        if (cars.isEmpty()) {
            return "CarCollection: пусто";
        }

        StringBuilder sb = new StringBuilder("CarCollection (" + size() + " авто):\n");
        for (int i = 0; i < cars.size(); i++) {
            sb.append("  ").append(i + 1).append(". ").append(cars.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Распечатать коллекцию в консоль
     */
    public void print() {
        System.out.println(this);
    }
}