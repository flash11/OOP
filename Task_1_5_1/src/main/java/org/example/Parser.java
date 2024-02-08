package org.example;

import exceptions.IllegalArgumentFoundException;
import exceptions.IllegalOperatorException;
import operations.Num;
import operations.Operation;

import java.util.Stack;

/**
 * parsing and structure stack.
 */
public class Parser {
    public static Operation parse(String expression) throws IllegalArgumentFoundException, IllegalOperatorException {

        String[] expressionArr = expression.split(" ");
        Stack<Operation> stack = new Stack<>();
        for (int i = expressionArr.length - 1; i >= 0; i--) {
            try {
                Double tmp = Double.parseDouble(expressionArr[i]);
                stack.push(new Num(tmp));
            } catch (NumberFormatException e) {
                if (expressionArr[i].equals("")) {
                    continue;
                }
                Operation o = OperationFactory.create(expressionArr[i]);
                o.setArgsFromStack(stack);
                stack.push(o);
            }
        }
        if (stack.size() == 1) {
            return stack.pop();
        } else {
            throw new IllegalArgumentFoundException("Неверный ответ");
        }
    }
}

