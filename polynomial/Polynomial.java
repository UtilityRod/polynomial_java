package com.polynomial;

import java.util.Iterator;

public class Polynomial implements Iterable<PolyTerm>, Comparable<Polynomial>{

    PolyTerm headTerm = null;

    public Polynomial(String polyString) throws InvalidPolynomialSyntax {
        String[] terms = polyString.split(" ");
        int termSize = terms.length;
        float coef = 0;
        int exp = 0;

        for(int i = 0; i < termSize; i+=2) {
            try {
                coef = Float.parseFloat(terms[i]);
            } catch (NumberFormatException e) {
                throw new InvalidPolynomialSyntax(String.format("Invalid coefficient '%s' for poly string '%s'", terms[i], polyString));
            }

            try {
                exp = Integer.parseInt(terms[i + 1]);
            } catch (NumberFormatException e) {
                throw new InvalidPolynomialSyntax(String.format("Invalid exponent '%s' for poly string '%s'", terms[i + 1], polyString));
            }

            PolyTerm term = new PolyTerm(coef, exp);
            this.insertTerm(term);
        }
    }

    public void insertTerm(PolyTerm newTerm) {
        
        if (this.headTerm == null) {
            this.headTerm = newTerm;
            return;
        }

        PolyTerm currentTerm = this.headTerm;

        while (true) {
            int compareResult = newTerm.compareExp(currentTerm);

            if (compareResult == 0) {
                // Exponents are the same
                currentTerm.add(newTerm);
                break;
            } else if (compareResult == -1) {
                // new term is larger than currentTerm insert in front of current term
                PolyTerm currentTermParent = currentTerm.getPrev();
                newTerm.setNext(currentTerm);
                newTerm.setPrev(currentTermParent);
                currentTerm.setPrev(newTerm);

                if (currentTermParent != null) {
                    currentTermParent.setNext(newTerm);
                }

                if (currentTerm == this.headTerm) {
                    this.headTerm = newTerm;
                }

                break;
            } else {
                PolyTerm nextTerm = currentTerm.getNext();

                if (nextTerm == null) {
                    currentTerm.setNext(newTerm);
                    newTerm.setPrev(currentTerm);
                    break;
                }

                currentTerm = nextTerm;
            }
        }
    }

    public String toString() {
        String returnString = "";
        for (PolyTerm term : this) {
            if (returnString.length() != 0) {
                returnString += " + ";
            }

            returnString += term;
        }

        return returnString;
    }

    public PolyTerm getHead() {
        return this.headTerm;
    }

    public Iterator<PolyTerm> iterator() {
        return new PolyIterator(this);
    }

    public int compareTo(Polynomial comparePoly) {
        int rv;
        PolyTerm myCurrent = this.headTerm;
        PolyTerm compareCurrent = comparePoly.getHead();

        while (true) {
            if (myCurrent == null && compareCurrent == null)
            {
                // Both polynomials no longer have elements
                rv = 0;
                break;
            } else if (myCurrent == null) {
                // comparePoly is larger
                rv = 1;
            } else if (compareCurrent == null) {
                // comparePoly is smaller
                rv = -1;
            } else {
                rv = myCurrent.compareExp(compareCurrent);

                if (rv == 0)
                {
                    rv = myCurrent.compareCoef(compareCurrent);
                }
            }

            if (rv != 0) {
                break;
            }

            myCurrent = myCurrent.getNext();
            compareCurrent = compareCurrent.getNext();
        }
        

        return rv;
    }
}
