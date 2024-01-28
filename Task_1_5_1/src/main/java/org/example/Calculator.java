package org.example;

import exceptions.*;


import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * class which calculate
 */
public class Calculator {
    /**
     * array of operations
     */
    public static String[] operations = new String[] {"+", "-", "*", "/", "log", "pow", "sqrt", "sin", "cos"};

    /**
     * method where scan input data and calculate
     */
    public static void calculator() {
    /**
    * using multi-catch that present all exceptions
     */
        while(true) {

            Scanner line = new Scanner(System.in);
            String expression = line.nextLine();

            try {
                Double result = calculateExpression(expression);

                System.out.println(result);

            } catch (DivisionByZeroException | SquareRootLessThanZeroException | IllegalArgumentFoundException |
                     LogarithmIncorrectArgumentException | IllegalOperatorException e) {

                System.out.println(e.getMessage());

            }

        }
    }

    /**
     * main logic of calculator using switch-cases
     * @param expression - input string
     * @return double expression
     * @throws DivisionByZeroException - Division By Zero
     * @throws IllegalArgumentFoundException - Illegal Argument Found
     * @throws LogarithmIncorrectArgumentException - Logarithm Incorrect Argument
     * @throws SquareRootLessThanZeroException - Square Root Less Than Zero
     * @throws IllegalOperatorException - Illegal Operator
     */
    public static Double calculateExpression(String expression) throws DivisionByZeroException, IllegalArgumentFoundException,
            LogarithmIncorrectArgumentException, SquareRootLessThanZeroException, IllegalOperatorException {

        String[] expressionArr = expression.split(" ");
        Stack<Double> stack = new Stack<>();
        Double a1;
        Double a2;
        for (int i = expressionArr.length - 1; i >= 0; i--) {
            try {
                Double tmp = Double.parseDouble(expressionArr[i]);
                stack.push(tmp);
            }
            catch (NumberFormatException e) {
                if (Arrays.asList(operations).contains(expressionArr[i])){
                    switch (expressionArr[i]) {
                        case("+"):
                            if (stack.size() < 2) {
                                throw new IllegalArgumentFoundException("Недостаточное количество аргументов");
                            }
                            a1 = stack.pop();
                            a2 = stack.pop();
                            stack.push(a1 + a2);
                            break;
                        case("-"):
                            if (stack.size() < 2) {
                                throw new IllegalArgumentFoundException("Недостаточное количество аргументов");
                            }
                            a1 = stack.pop();
                            a2 = stack.pop();
                            stack.push(a1 - a2);
                            break;
                        case("*"):
                            if (stack.size() < 2) {
                                throw new IllegalArgumentFoundException("Недостаточное количество аргументов");
                            }
                            a1 = stack.pop();
                            a2 = stack.pop();
                            stack.push(a1 * a2);
                            break;
                        case("/"):
                            if (stack.size() < 2) {
                                throw new IllegalArgumentFoundException("Недостаточное количество аргументов");
                            }
                            a1 = stack.pop();
                            a2 = stack.pop();
                            if (a2.equals(0.0)) {
                                throw new DivisionByZeroException("Нельзя делить на ноль");
                            }
                            stack.push(a1 / a2);
                            break;
                        case("log"):
                            if (stack.size() < 1) {
                                throw new IllegalArgumentFoundException("Недостаточное количество аргументов");
                            }
                            a1 = stack.pop();
                            if (a1 <= 0) {
                                throw new LogarithmIncorrectArgumentException("Аргумент логарифма должен быть больше 0");
                            }
                            stack.push(Math.log10(a1));
                            break;
                        case("pow"):
                            if (stack.size() < 2) {
                                throw new IllegalArgumentFoundException("Недостаточное количество аргументов");
                            }
                            a1 = stack.pop();
                            a2 = stack.pop();
                            stack.push(Math.pow(a1, a2));
                            break;
                        case("sqrt"):
                            if (stack.size() < 1) {
                                throw new IllegalArgumentFoundException("Недостаточное количество аргументов");
                            }
                            a1 = stack.pop();
                            if (a1 < 0) {
                                throw new SquareRootLessThanZeroException("Корень не может быть меньше 0");
                            }
                            stack.push(Math.sqrt(a1));
                            break;
                        case("sin"):
                            if (stack.size() < 1) {
                                throw new IllegalArgumentFoundException("Недостаточное количество аргументов");
                            }
                            a1 = stack.pop();
                            stack.push(Math.sin(a1));
                            break;
                        case("cos"):
                            if (stack.size() < 1) {
                                throw new IllegalArgumentFoundException("Недостаточное количество аргументов");
                            }
                            a1 = stack.pop();
                            stack.push(Math.cos(a1));
                            break;
                    }
                }
                else {
                    if (expressionArr[i].equals("")) {
                        continue;
                    }
                    throw new IllegalOperatorException("Неизвестный оператор " + expressionArr[i]);
                }
            }
        }
        if (stack.size() == 1) {
            return stack.pop();
        }
        else {
            throw new IllegalArgumentFoundException("Неверный ответ");
        }
    }
}
