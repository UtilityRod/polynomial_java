package com.polynomial;

import java.util.Iterator;

public class PolyIterator implements Iterator<PolyTerm>{
    PolyTerm current = null;

    public PolyIterator(Polynomial poly) {
        this.current = poly.getHead();
    }

    public boolean hasNext() {
        return this.current != null;
    }

    public PolyTerm next() {
        PolyTerm returnTerm = this.current;
        this.current = this.current.getNext();
        return returnTerm;
    }
}
