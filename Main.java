package com;

import com.polynomial.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage:\njava com.Main [FILE]\n\nFILE - The file to read polynomials from.");
            return;
        }

        Comparator<Polynomial> compare = (Polynomial poly1, Polynomial poly2) -> {
            int rv;
            PolyTerm poly1Current = poly1.getHead();
            PolyTerm poly2Current = poly2.getHead();

            while (true) {
                if (poly1Current == null && poly2Current == null)
                {
                    // Both polynomials no longer have elements
                    rv = 0;
                    break;
                } else if (poly1Current == null) {
                    // comparePoly is larger
                    rv = 1;
                } else if (poly2Current == null) {
                    // comparePoly is smaller
                    rv = -1;
                } else {
                    rv = poly1Current.compareExp(poly2Current);
                }
    
                if (rv != 0) {
                    break;
                }
    
                poly1Current = poly1Current.getNext();
                poly2Current = poly2Current.getNext();
            }

            return rv;
        };

        Polynomial tmpPoly = null;
        ArrayList<Polynomial> polyArrayList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(args[0]));

            while (scanner.hasNext()) {

                tmpPoly = new Polynomial(scanner.nextLine());
                System.out.println(tmpPoly);
                polyArrayList.add(tmpPoly);
            }

            boolean strongSorted = OrderedList.checkSorted(polyArrayList);
            boolean weakSorted = OrderedList.checkSorted(polyArrayList, compare);

            System.out.printf("Strong sorted: %b\n", strongSorted);
            System.out.printf("Weak sorted: %b\n", weakSorted);

        } catch (FileNotFoundException e) {
            System.out.printf("ERROR: File %s does not exist.\n", args[0]);
        } catch (InvalidPolynomialSyntax e) {
            System.out.println(e.getMessage());
        }
    }
}
