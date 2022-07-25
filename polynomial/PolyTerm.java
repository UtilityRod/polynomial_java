package com.polynomial;

public class PolyTerm {
    float coef = 0;
    int exp = 0;
    PolyTerm next = null;
    PolyTerm prev = null;

    public PolyTerm(float newCoef, int newExp) {
        this.coef = newCoef;
        this.exp = newExp;
    }

    public float getCoef() {
        return this.coef;
    }

    public int getExp() {
        return this.exp;
    }

    public PolyTerm getNext() {
        return this.next;
    }

    public void setNext(PolyTerm newNext) {
        this.next = newNext;
    }

    public PolyTerm getPrev() {
        return this.prev;
    }

    public void setPrev(PolyTerm newPrev) {
        this.prev = newPrev;
    }

    public String toString() {
        String format = "%.1f";

        if ((this.coef - (int)this.coef) == 0)
        {
            format = "%.0f";
        }

        String returnString = String.format(format, this.coef);
        
        if (this.exp != 0) {
            returnString += String.format("x^%d", this.exp);
        }

        return returnString;
    }

    public int compareExp(PolyTerm compareTerm) {
        int rv;
        int myExp = this.exp;
        int compareExp = compareTerm.getExp();

        if (myExp > compareExp) {
            rv = -1;
        } else if (myExp < compareExp) {
            rv = 1;
        } else {
            rv = 0;
        }

        return rv;
    }

    public int compareCoef(PolyTerm compareTerm) {
        int rv;
        float myCoef = this.coef;
        float compareCoef = compareTerm.getCoef();

        if (myCoef > compareCoef) {
            rv = -1;
        } else if (myCoef < compareCoef) {
            rv = 1;
        } else {
            rv = 0;
        }

        return rv;
    }

    public void add(PolyTerm addTerm) {
        this.coef += addTerm.coef;
    }
}
