package org.example;

import exceptions.IllegalOperatorException;

import operations.Arctg;
import operations.Cos;
import operations.Division;
import operations.Log;
import operations.Minus;
import operations.Multiplication;
import operations.Negative;
import operations.Operation;
import operations.Pow;
import operations.Plus;
import operations.Sin;
import operations.Sqrt;

/**
 * factory.
 */
public class OperationFactory {
    /**
    * create method.
    */
    public static Operation create(String operation) throws IllegalOperatorException {
        switch (operation) {
            case "cos": return new Cos();
            case "sin": return new Sin();
            case "log": return new Log();
            case "pow": return new Pow();
            case "atan": return new Arctg();
            case "sqrt": return new Sqrt();
            case "-": return new Minus();
            case "*": return new Multiplication();
            case "/": return new Division();
            case "+": return new Plus();
            case "!": return new Negative();
            default: throw new IllegalOperatorException("Неизвестный оператор " + operation);
        }
    }
}

