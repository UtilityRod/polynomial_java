package com.polynomial;

import java.util.Comparator;
import java.util.ArrayList;

public class OrderedList {

    public static boolean checkSorted(ArrayList<Polynomial> polyArrayList){

        Comparator<Polynomial> compare = (Polynomial poly1, Polynomial poly2) -> {
            return poly1.compareTo(poly2);
        };

        return checkSorted(polyArrayList, compare);
    }

    public static boolean checkSorted(ArrayList<Polynomial> polyArrayList, Comparator<? super Polynomial> comparator) {
        boolean sorted = true;
        for (int i = 0; i < polyArrayList.size() - 1; i++)
        {
            int compareSorted = comparator.compare(polyArrayList.get(i), polyArrayList.get(i + 1));
            
            if (compareSorted == -1) {
                sorted = false;
            }
        }
        
        return sorted;
    }
    
}
