package operations;

import exceptions.ArithmeticException;
import exceptions.IllegalArgumentFoundException;
import java.util.Stack;

/**
 * calculate cos.
 */
public class Cos extends Operation {

    private Operation expression;

    /**
     * count.
     *
     * @return cos.
     * @throws ArithmeticException exception.
     */
    @Override
    public double count() throws ArithmeticException {
        return Math.cos(expression.count());
    }

    /**
     * set from stack.
     *
     * @param stack.
     * @throws IllegalArgumentFoundException exception.
     */
    @Override
    public void setArgsFromStack(Stack<Operation> stack) throws IllegalArgumentFoundException {
        if (stack.size() < 1) {
            throw new IllegalArgumentFoundException("Недостаточное количество аргументов");
        }
        expression = stack.pop();
    }
}
