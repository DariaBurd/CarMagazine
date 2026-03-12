package com.carmagazine.sort;

import java.util.List;

public class SortByYear implements SortStrategy {
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
