//интерфейс стратегии, три стратегии сортировки
//
// Автомобиль {" +
//                "мощность=" + power +
//                ", год=" + year +
//                ", комплектация='" + configuration + '\'' +
//                ", цвет='" + color

import java.util.List;

public interface SortStrategy {
    void sort(List<Automobile> cars);
}

//сортировка по мощности
class SortByPower implements SortStrategy {

    @Override
    public void sort(List<Automobile> cars) {

    }
}
//сортировка по году
class SortByYear implements SortStrategy{

    @Override
    public void sort(List<Automobile> cars) {

    }
}

//сортировка по комплектации
class SortByConfiguration implements SortStrategy{

    @Override
    public void sort(List<Automobile> cars) {

    }
}

//сортировка по цвету
class SortByColor implements SortStrategy{

    @Override
    public void sort(List<Automobile> cars) {

    }
}

