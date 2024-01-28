package org.example;

import exceptions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    public void testFunctions() throws DivisionByZeroException, IllegalArgumentFoundException,
            IllegalOperatorException, LogarithmIncorrectArgumentException, SquareRootLessThanZeroException {
        Double res = Calculator.calculateExpression("sin cos sqrt log + 1 - 12 * 3 / 4 5");
        Assertions.assertEquals(Math.round(res * 1000), 505);
    }

    @Test
    public void testAdd() throws DivisionByZeroException, IllegalArgumentFoundException,
            IllegalOperatorException, LogarithmIncorrectArgumentException, SquareRootLessThanZeroException {
        Double res  = Calculator.calculateExpression("+ 11 -45");
        Assertions.assertEquals(res, -34);
    }

    @Test
    public void testMinus() throws DivisionByZeroException, IllegalArgumentFoundException,
            IllegalOperatorException, LogarithmIncorrectArgumentException, SquareRootLessThanZeroException {
        Double res  = Calculator.calculateExpression("- 11 -45");
        Assertions.assertEquals(res, 56);
    }

    @Test
    public void testMul() throws DivisionByZeroException, IllegalArgumentFoundException,
            IllegalOperatorException, LogarithmIncorrectArgumentException, SquareRootLessThanZeroException {
        Double res  = Calculator.calculateExpression("* 10 -45");
        Assertions.assertEquals(res, -450);
    }

    @Test
    public void testDiv() throws DivisionByZeroException, IllegalArgumentFoundException,
            IllegalOperatorException, LogarithmIncorrectArgumentException, SquareRootLessThanZeroException {
        Double res  = Calculator.calculateExpression("/ 121 11");
        Assertions.assertEquals(res, 11);
    }

    @Test
    public void testSin() throws DivisionByZeroException, IllegalArgumentFoundException,
            IllegalOperatorException, LogarithmIncorrectArgumentException, SquareRootLessThanZeroException {
        Double res  = Calculator.calculateExpression("sin 0");
        Assertions.assertEquals(res, 0);
    }

    @Test
    public void testCos() throws DivisionByZeroException, IllegalArgumentFoundException,
            IllegalOperatorException, LogarithmIncorrectArgumentException, SquareRootLessThanZeroException {
        Double res  = Calculator.calculateExpression("cos 0");
        Assertions.assertEquals(res, 1);
    }

    @Test
    public void testLog() throws DivisionByZeroException, IllegalArgumentFoundException,
            IllegalOperatorException, LogarithmIncorrectArgumentException, SquareRootLessThanZeroException {
        Double res  = Calculator.calculateExpression("log 145");
        Assertions.assertEquals(Math.round(res * 1000), 2161);
    }

    @Test
    public void testPow() throws DivisionByZeroException, IllegalArgumentFoundException,
            IllegalOperatorException, LogarithmIncorrectArgumentException, SquareRootLessThanZeroException {
        Double res  = Calculator.calculateExpression("pow 2 10");
        Assertions.assertEquals(res, 1024);
    }

    @Test
    public void testSqrt() throws DivisionByZeroException, IllegalArgumentFoundException,
            IllegalOperatorException, LogarithmIncorrectArgumentException, SquareRootLessThanZeroException {
        Double res  = Calculator.calculateExpression("sqrt 1024");
        Assertions.assertEquals(res, 32);
    }


    @Test
    public void testWhiteSpace() throws DivisionByZeroException, IllegalArgumentFoundException,
            IllegalOperatorException, LogarithmIncorrectArgumentException, SquareRootLessThanZeroException {
        Double res = Calculator.calculateExpression("    +   9       9");
        Assertions.assertEquals(res, 18.0);
    }
}