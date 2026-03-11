package sort;//интерфейс стратегии, три стратегии сортировки
//
// Автомобиль {" +
//                "мощность=" + power +
//                ", год=" + year +
//                ", комплектация='" + configuration + '\'' +
//                ", цвет='" + color



import automobile.Automobile;
import fileSaver.FileSaver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface SortStrategy {
    void sort(List<Automobile> automobiles);
}

//сортировка по мощности
class SortByPower implements SortStrategy {

    @Override
    public void sort(List<Automobile> automobiles) {
        if (automobiles == null || automobiles.size() <= 1) {
            return;
        }

        int n = automobiles.size();
        for (int i = 0; i < n-1; i++) {
            boolean swap = false;

            for (int j = 0; j < n-i-1; j++) {
                if (automobiles.get(j).getPower() > automobiles.get(j+1).getPower()){
                    Automobile t = automobiles.get(j);
                    automobiles.set(j, automobiles.get(j+1));
                    automobiles.set(j+1, t);
                    swap = true;
                }
            }
            if (!swap) break;
        }
    }
}
//сортировка по году
class SortByYear implements SortStrategy{

    @Override
    public void sort(List<Automobile> automobiles) {
        if (automobiles == null || automobiles.size() <= 1) {
            return;
        }

        int n = automobiles.size();
        for (int i = 0; i < n-1; i++) {
            boolean swap = false;

            for (int j = 0; j < n-i-1; j++) {
                if (automobiles.get(j).getYear() > automobiles.get(j+1).getYear()){
                    Automobile t = automobiles.get(j);
                    automobiles.set(j, automobiles.get(j+1));
                    automobiles.set(j+1, t);
                    swap = true;
                }
            }
            if (!swap) break;
        }
    }
}

//сортировка по комплектации
class SortByConfiguration implements SortStrategy{

    @Override
    public void sort(List<Automobile> automobiles) {
        if (automobiles == null || automobiles.size() <= 1) {
            return;
        }

        int n = automobiles.size();
        for (int i = 0; i < n-1; i++) {
            boolean swap = false;

            for (int j = 0; j < n-i-1; j++) {
                String config1 = automobiles.get(j).getConfiguration();
                String config2 = automobiles.get(j+1).getConfiguration();
                if (config1.compareToIgnoreCase(config2) > 0 ){
                    Automobile t = automobiles.get(j);
                    automobiles.set(j, automobiles.get(j+1));
                    automobiles.set(j+1, t);
                    swap = true;
                }
            }
            if (!swap) break;
        }
    }
}

//сортировка по цвету
class SortByColor implements SortStrategy{

    @Override
    public void sort(List<Automobile> automobiles) {
        if (automobiles == null || automobiles.size() <= 1) {
            return;
        }

        int n = automobiles.size();
        for (int i = 0; i < n-1; i++) {
            boolean swap = false;

            for (int j = 0; j < n-i-1; j++) {
                String config1 = automobiles.get(j).getColor();
                String config2 = automobiles.get(j+1).getColor();
                if (config1.compareToIgnoreCase(config2) > 0 ){
                    Automobile t = automobiles.get(j);
                    automobiles.set(j, automobiles.get(j+1));
                    automobiles.set(j+1, t);
                    swap = true;
                }
            }
            if (!swap) break;
        }
    }
}

//сортировка по цвету доп чет нечет
//дополнительно к основным сортировкам реализовать эти же алгоритмы сортировки таким образом,
// что объекты классов будут сортироваться по какому-либо числовому полю: объекты с четными
// значениями этого поля должны быть отсортированы в натуральном порядке, а с нечетными
// оставаться на исходных позициях.

//Чётные годы сортируются по возрастанию, нечётные остаются на местах
class AdditionSortByYear implements SortStrategy{

    @Override
    public void sort(List<Automobile> automobiles) {
        if (automobiles == null || automobiles.size() <= 1) {
            return;
        }

        List<Automobile> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        //четные
        for (int i = 0; i < automobiles.size(); i++) {
            Automobile auto = automobiles.get(i);
            if (auto.getYear() % 2 ==0) {
                a.add(auto);
                b.add(i);
            }
        }

        int n = a.size();
        for (int i = 0; i < n-1; i++) {
            boolean swap = false;

            for (int j = 0; j < n-i-1; j++) {
                if (a.get(j).getYear() > a.get(j+1).getYear()){
                    Automobile t = a.get(j);
                    a.set(j, a.get(j+1));
                    a.set(j+1, t);
                    swap = true;
                }
            }
            if (!swap) break;
        }

        for (int i = 0; i < a.size(); i++) {
            automobiles.set(b.get(i), a.get(i));
        }
    }
}


class AutomobileSort {
    private SortStrategy strSort;
    private final Scanner scanner = new Scanner(System.in);

    public AutomobileSort() {

    }

    public void setStrategy(SortStrategy strategy) {
        this.strSort = strategy;
    }

    public List<Automobile> sort(List<Automobile> autos) {
        if (strSort == null) {
            throw new IllegalStateException("Стратегия сортировки не установлена!");
        }
        if (autos == null) {
            throw new IllegalArgumentException("Список автомобилей не может быть null!");
        }
        if (autos.isEmpty()) {
            System.out.println("Предупреждение: сортировка пустого списка");
            return new ArrayList<>();  // Возвращаем пустой список
        }

        List<Automobile> sortedCopy = new ArrayList<>(autos);
        strSort.sort(sortedCopy);
        return sortedCopy;
    }

    // Сохранение в файл
    public void save(List<Automobile> autos) {
        System.out.print("Сохранить отсортированную коллекцию в файл? (да/нет): ");
        String saving = scanner.nextLine().trim().toLowerCase();
        if (saving.equals("да") || saving.equals("yes")) {
            // Получаем простое имя класса стратегии для понятного описания
            String sortName = strSort.getClass().getSimpleName();
            FileSaver.saveSortedCars("sort_results.txt", autos, sortName);
        }
    }

    public void print(List<Automobile> autos, String title, boolean askToSave) {
        System.out.println("\n" + title);
        List<Automobile> sorted = sort(autos);
        sorted.forEach(System.out::println);

        if (askToSave) {
            save(sorted);
        }

        System.out.println("_________________");
    }
}

class Test {
    public static void main(String[] args) {
        List<Automobile> testList = new ArrayList<>();

        testList.add(new Automobile.Builder().setPower(180).setYear(2001).setConfiguration("Comfort").setColor("Синий").build());
        testList.add(new Automobile.Builder().setPower(250).setYear(2013).setConfiguration("Prestige").setColor("Чёрный").build());
        testList.add(new Automobile.Builder().setPower(120).setYear(2016).setConfiguration("Base").setColor("Красный").build());
        testList.add(new Automobile.Builder().setPower(200).setYear(2018).setConfiguration("Limited").setColor("Белый").build());
        testList.add(new Automobile.Builder().setPower(180).setYear(2018).setConfiguration("Luxury").setColor("Зелёный").build());
        testList.add(new Automobile.Builder().setPower(150).setYear(2013).setConfiguration("Base").setColor("Серебристый").build());
        testList.add(new Automobile.Builder().setPower(320).setYear(2025).setConfiguration("Sport").setColor("Красный").build());
        testList.add(new Automobile.Builder().setPower(190).setYear(2024).setConfiguration("Comfort").setColor("Чёрный").build());
        testList.add(new Automobile.Builder().setPower(110).setYear(2009).setConfiguration("Classic").setColor("Белый").build());
        testList.add(new Automobile.Builder().setPower(280).setYear(2018).setConfiguration("Premium").setColor("Синий").build());
        testList.add(new Automobile.Builder().setPower(200).setYear(2026).setConfiguration("Luxury").setColor("Зелёный").build());
        testList.add(new Automobile.Builder().setPower(240).setYear(2019).setConfiguration("Elite").setColor("Серый").build());
        testList.add(new Automobile.Builder().setPower(170).setYear(2016).setConfiguration("Standard").setColor("Жёлтый").build());
        testList.add(new Automobile.Builder().setPower(300).setYear(2023).setConfiguration("Standard").setColor("Оранжевый").build());
        testList.add(new Automobile.Builder().setPower(130).setYear(2014).setConfiguration("Premium").setColor("Коричневый").build());
        testList.add(new Automobile.Builder().setPower(260).setYear(2014).setConfiguration("Sport").setColor("Фиолетовый").build());
        testList.add(new Automobile.Builder().setPower(210).setYear(2023).setConfiguration("Base").setColor("Металлик").build());

        System.out.println("Исходный список авто:");
        testList.forEach(System.out::println);
        System.out.println("_________________");


        AutomobileSort sorter = new AutomobileSort();

        sorter.setStrategy(new SortByPower());
        sorter.print(testList, "Cортировка по мощности:", false);

        sorter.setStrategy(new SortByYear());
        sorter.print(testList, "Сортировка по году:", false);

        sorter.setStrategy(new SortByConfiguration());
        sorter.print(testList, "Сортировка по комплектации:", false);

        sorter.setStrategy(new SortByColor());
        sorter.print(testList, "Сортировка по цвету:", false);

        sorter.setStrategy(new AdditionSortByYear());
        sorter.print(testList, "доп задание: сортировка чётные годы сортируются по возрастанию, нечётные остаются на местах", false);

    }
}