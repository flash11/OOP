package org.example;

import exceptions.*;

import exceptions.ArithmeticException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import operations.Negative;
import operations.Num;
import operations.Operation;
import java.util.Stack;


public class CalculatorTest {

    @Test
    public void testFunctions() throws ArithmeticException, IllegalArgumentFoundException,
            IllegalOperatorException {
        Double res = Calculator.calculateExpression("cos sqrt + 1 - 12 * 3 4");
        Assertions.assertEquals(Math.round(res * 10000), 5403);
    }

    @Test
    public void testAdd() throws ArithmeticException, IllegalArgumentFoundException,
            IllegalOperatorException {
        Double res  = Calculator.calculateExpression("+ 11 -45");
        Assertions.assertEquals(res, -34);
    }

    @Test
    public void testNegative() throws IllegalArgumentFoundException, ArithmeticException {
        // Подготавливаем стек с операциями, содержащий одно число
        Stack<Operation> stack = new Stack<>();
        stack.push(new Num(10)); // Положительное число
        Negative negativeOperation = new Negative();
        negativeOperation.setArgsFromStack(stack);
        Assertions.assertEquals(-10, negativeOperation.count(), "Negative of 10 should be -10");

        stack.push(new Num(-20)); // Отрицательное число
        negativeOperation.setArgsFromStack(stack);
        Assertions.assertEquals(20, negativeOperation.count(), "Negative of -20 should be 20");
    }

    @Test
    public void testArctg() throws ArithmeticException, IllegalArgumentFoundException,
            IllegalOperatorException {
        Double res = Calculator.calculateExpression("atan 1");
        Assertions.assertEquals(Math.round(res * 10000), Math.round(Math.PI / 4 * 10000));
    }
    
    @Test
    public void testMinus() throws ArithmeticException, IllegalArgumentFoundException,
            IllegalOperatorException {
        Double res  = Calculator.calculateExpression("- 11 -45");
        Assertions.assertEquals(res, 56);
    }

    @Test
    public void testMul() throws ArithmeticException, IllegalArgumentFoundException,
            IllegalOperatorException {
        Double res  = Calculator.calculateExpression("* 10 -45");
        Assertions.assertEquals(res, -450);
    }

    @Test
    public void testDiv() throws ArithmeticException, IllegalArgumentFoundException,
            IllegalOperatorException {
        Double res  = Calculator.calculateExpression("/ 121 11");
        Assertions.assertEquals(res, 11);
    }

    @Test
    public void testSin() throws ArithmeticException, IllegalArgumentFoundException,
            IllegalOperatorException {
        Double res  = Calculator.calculateExpression("sin 0");
        Assertions.assertEquals(res, 0);
    }

    @Test
    public void testCos() throws ArithmeticException, IllegalArgumentFoundException,
            IllegalOperatorException {
        Double res  = Calculator.calculateExpression("cos 0");
        Assertions.assertEquals(res, 1);
    }

    @Test
    public void testLog() throws ArithmeticException, IllegalArgumentFoundException,
            IllegalOperatorException {
        Double res  = Calculator.calculateExpression("log 145");
        Assertions.assertEquals(Math.round(res * 1000), 4977);
    }

    @Test
    public void testPow() throws ArithmeticException, IllegalArgumentFoundException,
            IllegalOperatorException {
        Double res  = Calculator.calculateExpression("pow 2 10");
        Assertions.assertEquals(res, 1024);
    }

    

    @Test
    public void testSqrt() throws ArithmeticException, IllegalArgumentFoundException,
            IllegalOperatorException {
        Double res  = Calculator.calculateExpression("sqrt 1024");
        Assertions.assertEquals(res, 32);
    }


    @Test
    public void testWhiteSpace() throws ArithmeticException, IllegalArgumentFoundException,
            IllegalOperatorException {
        Double res = Calculator.calculateExpression("    +   9       9");
        Assertions.assertEquals(res, 18.0);
    }

    //
     @Test
    public void testAddition() throws IllegalArgumentFoundException, ArithmeticException, IllegalOperatorException {
        Double result = Calculator.calculateExpression("+ 1 2");
        Assertions.assertEquals(3.0, result, "1 + 2 should be 3");
    }

    @Test
    public void testSubtraction() throws IllegalArgumentFoundException, ArithmeticException, IllegalOperatorException {
        Double result = Calculator.calculateExpression("- 5 2");
        Assertions.assertEquals(3.0, result, "5 - 2 should be 3");
    }

    @Test
    public void testMultiplication() throws IllegalArgumentFoundException, ArithmeticException, IllegalOperatorException {
        Double result = Calculator.calculateExpression("* 3 4");
        Assertions.assertEquals(12.0, result, "3 * 4 should be 12");
    }

    @Test
    public void testDivision() throws IllegalArgumentFoundException, ArithmeticException, IllegalOperatorException {
        Double result = Calculator.calculateExpression("/ 8 2");
        Assertions.assertEquals(4.0, result, "8 / 2 should be 4");
    }
}
