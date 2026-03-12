package com.carmagazine.sort;

import java.util.ArrayList;
import java.util.List;

//сортировка по цвету доп чет нечет
//дополнительно к основным сортировкам реализовать эти же алгоритмы сортировки таким образом,
// что объекты классов будут сортироваться по какому-либо числовому полю: объекты с четными
// значениями этого поля должны быть отсортированы в натуральном порядке, а с нечетными
// оставаться на исходных позициях.

//Чётные годы сортируются по возрастанию, нечётные остаются на местах


public class AdditionSortByYear implements SortStrategy {
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
