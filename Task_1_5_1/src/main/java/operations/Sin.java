package operations;

import exceptions.ArithmeticException;
import exceptions.IllegalArgumentFoundException;

import java.util.Stack;

/**
 * sin.
 */
public class Sin extends Operation {

    private Operation expression;

    /**
     * count.
     *
     * @return expression.
     * @throws ArithmeticException
     */
    @Override
    public double count() throws ArithmeticException {
        return Math.sin(expression.count());
    }

    /**
     * set from stack.
     *
     * @param stack
     * @throws IllegalArgumentFoundException
     */
    @Override
    public void setArgsFromStack(Stack<Operation> stack) throws IllegalArgumentFoundException {
        if (stack.size() < 1) {
            throw new IllegalArgumentFoundException("Недостаточное количество аргументов");
        }
        expression = stack.pop();
    }
}