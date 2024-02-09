package org.example;

import exceptions.ArithmeticException;
import exceptions.DivisionByZeroException;
import exceptions.IllegalArgumentFoundException;
import exceptions.IllegalOperatorException;
import exceptions.LogarithmIncorrectArgumentException;
import exceptions.SquareRootLessThanZeroException;
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

    @Test
    public void testDivisionByZero() {
        Assertions.assertThrows(DivisionByZeroException.class, () -> { 
            Calculator.calculateExpression("/ 1 0");
        }, "Division by zero should throw DivisionByZeroException");
    }

     @Test
    public void testCombinedOperations() throws IllegalArgumentFoundException, ArithmeticException, IllegalOperatorException {
        Double result = Calculator.calculateExpression("+ * 2 3 4");
        Assertions.assertEquals(10.0, result, "2 * 3 + 4 should be 10");
    }

    @Test
    public void testUnknownOperation() {
        Assertions.assertThrows(IllegalOperatorException.class, () -> {
            Calculator.calculateExpression("unknown 2 3");
        }, "Unknown operation should throw IllegalOperatorException");
    }

    @Test
    public void testLargeNumbers() throws IllegalArgumentFoundException, ArithmeticException, IllegalOperatorException {
        Double result = Calculator.calculateExpression("+ 99999999 1");
        Assertions.assertEquals(100000000.0, result, "99999999 + 1 should be 100000000");
}

    @Test
    public void testSmallNumbers() throws IllegalArgumentFoundException, ArithmeticException, IllegalOperatorException {
        Double result = Calculator.calculateExpression("* 0.0001 0.0002");
        Assertions.assertEquals(0.00000002, result, "0.0001 * 0.0002 should be 0.00000002");
}

        
    @Test
    public void testLogarithmOfNegativeNumberThrowsException() {
        Assertions.assertThrows(LogarithmIncorrectArgumentException.class, () -> {
            Calculator.calculateExpression("log -1");
        }, "Logarithm of a negative number should throw LogarithmIncorrectArgumentException");
    }

    @Test
    public void testSquareRootOfNegativeNumberThrowsException() {
    Assertions.assertThrows(SquareRootLessThanZeroException.class, () -> {
        Calculator.calculateExpression("sqrt -4");
    }, "Square root of a negative number should throw SquareRootLessThanZeroException");
}

    @Test
    public void testOperationWithInsufficientArgumentsThrowsException() {
    Assertions.assertThrows(IllegalArgumentFoundException.class, () -> {
        Calculator.calculateExpression("+ 5"); // Только один аргумент вместо двух
    }, "Operation with insufficient arguments should throw IllegalArgumentFoundException");
}


}
