package org.example;

import exceptions.IllegalArgumentFoundException;
import exceptions.LogarithmIncorrectArgumentException;
import exceptions.IllegalOperatorException;
import exceptions.DivisionByZeroException;
import exceptions.SquareRootLessThanZeroException;
import exceptions.ArithmeticException;
import operations.Operation;

import java.util.Scanner;


/**
 * class which calculate.
 */
public class Calculator {

    /**
     * method where scan input data and calculate.
     */
    public static void calculator() {
    /**
    * using multi-catch that present all exceptions.
     */
        while(true) {

            Scanner line = new Scanner(System.in);
            String expression = line.nextLine();

            try {
                Double result = calculateExpression(expression);
                System.out.println(result);

            } catch (ArithmeticException | IllegalArgumentFoundException |
                     IllegalOperatorException e) {

                System.out.println(e.getMessage());

            }
        }
    }

    /**
     * main logic of calculator.
     * @param expression input string
     * @return double expression.
     * @throws DivisionByZeroException Division By Zero
     * @throws IllegalArgumentFoundException Illegal Argument Found
     * @throws LogarithmIncorrectArgumentException Logarithm Incorrect Argument
     * @throws SquareRootLessThanZeroException Square Root Less Than Zero
     * @throws IllegalOperatorException Illegal Operator
     */
    public static Double calculateExpression(String expression) throws IllegalArgumentFoundException,
            ArithmeticException, IllegalOperatorException {

        Operation operation = Parser.parse(expression);

        return operation.count();
    }
}
