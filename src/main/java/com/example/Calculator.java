package com.example;

public class Calculator {

    public int calculate(int a, int b, String operation) {

        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }

        switch (operation.toLowerCase()) {
            case "add":
                return add(a, b);

            case "sub":
                return subtract(a, b);

            case "mul":
                return multiply(a, b);

            case "div":
                return divide(a, b);

            case "mod":
                return modulo(a, b);

            case "pow":
                return power(a, b);

            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }

    private int add(int a, int b) {
        return a + b;
    }

    private int subtract(int a, int b) {
        return a - b;
    }

    private int multiply(int a, int b) {
        return a * b;
    }

    private int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    }

    private int modulo(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Modulo by zero is not allowed");
        }
        return a % b;
    }

    private int power(int base, int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent must be non-negative");
        }

        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
}
