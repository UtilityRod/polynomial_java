package com.polynomial;

public class InvalidPolynomialSyntax extends Exception{
    public InvalidPolynomialSyntax () {}

    public InvalidPolynomialSyntax (String message) {
        super(message);
    }
}
