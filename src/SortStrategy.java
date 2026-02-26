//интерфейс стратегии, три стратегии сортировки
//
// Автомобиль {" +
//                "мощность=" + power +
//                ", год=" + year +
//                ", комплектация='" + configuration + '\'' +
//                ", цвет='" + color



import java.util.ArrayList;
import java.util.List;

public interface SortStrategy {
    void sort(List<Automobile> automobiles);
}

//сортировка по мощности
static class SortByPower implements SortStrategy {

    @Override
    public void sort(List<Automobile> automobiles) {
        if (automobiles == null || automobiles.size() <= 1) {
            return;
        }

        int n = automobiles.size();
        for (int i = 0; i < n; i++) {
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
static class SortByYear implements SortStrategy{

    @Override
    public void sort(List<Automobile> automobiles) {
        if (automobiles == null || automobiles.size() <= 1) {
            return;
        }

        int n = automobiles.size();
        for (int i = 0; i < n; i++) {
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
static class SortByConfiguration implements SortStrategy{

    @Override
    public void sort(List<Automobile> automobiles) {
        if (automobiles == null || automobiles.size() <= 1) {
            return;
        }

        int n = automobiles.size();
        for (int i = 0; i < n; i++) {
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
static class SortByColor implements SortStrategy{

    @Override
    public void sort(List<Automobile> automobiles) {
        if (automobiles == null || automobiles.size() <= 1) {
            return;
        }

        int n = automobiles.size();
        for (int i = 0; i < n; i++) {
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

void main(String[] args) {
    List<Automobile> testList = new ArrayList<>();

    testList.add(new Automobile.Builder().setPower(180).setYear(2019).setConfiguration("Comfort").setColor("Синий").build());
    testList.add(new Automobile.Builder().setPower(250).setYear(2023).setConfiguration("Prestige").setColor("Чёрный").build());
    testList.add(new Automobile.Builder().setPower(120).setYear(2012).setConfiguration("Base").setColor("Красный").build());
    testList.add(new Automobile.Builder().setPower(200).setYear(2021).setConfiguration("Limited").setColor("Белый").build());
    testList.add(new Automobile.Builder().setPower(180).setYear(2018).setConfiguration("Luxury").setColor("Зелёный").build());

    System.out.println("До сортировки:");
    testList.forEach(System.out::println);
    System.out.println("____________________");

    System.out.println("\nПосле сортировки по мощности:");
    new SortByPower().sort(testList);
    testList.forEach(System.out::println);
    System.out.println("____________________");

    System.out.println("\nПосле сортировки по году:");
    new SortByYear().sort(testList);
    testList.forEach(System.out::println);
    System.out.println("____________________");

    System.out.println("\nПосле сортировки по комплектации:");
    new SortByConfiguration().sort(testList);
    testList.forEach(System.out::println);
    System.out.println("____________________");

    System.out.println("\nПосле сортировки по цвету:");
    new SortByColor().sort(testList);
    testList.forEach(System.out::println);
    System.out.println("____________________");
}