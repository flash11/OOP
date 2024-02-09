package org.example;

import exceptions.ArithmeticException;
import exceptions.DivisionByZeroException;
import exceptions.IllegalArgumentFoundException;
import exceptions.IllegalOperatorException;
import exceptions.LogarithmIncorrectArgumentException;
import exceptions.SquareRootLessThanZeroException;
import java.util.Scanner;
import operations.Operation;

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
        while (true) {

            Scanner line = new Scanner(System.in);
            String expression = line.nextLine();

            try {
                Double result = calculateExpression(expression);
                System.out.println(result);

            } catch (ArithmeticException 
                     | IllegalArgumentFoundException 
                     | IllegalOperatorException e) {

                System.out.println(e.getMessage());

            }
        }
    }

    /**
     * main logic of calculator.
     *
     * @param expression input string
     * @return double expression.
     * @throws DivisionByZeroException Division By Zero
     * @throws IllegalArgumentFoundException Illegal Argument Found
     * @throws LogarithmIncorrectArgumentException LogarithmIncorrectArgument
     * @throws SquareRootLessThanZeroException SquareRootLessThanZero
     * @throws IllegalOperatorException Illegal Operator
     */
    public static Double calculateExpression(String expression) throws IllegalArgumentFoundException,
            ArithmeticException, IllegalOperatorException {

        Operation operation = Parser.parse(expression);

        return operation.count();
    }
}
