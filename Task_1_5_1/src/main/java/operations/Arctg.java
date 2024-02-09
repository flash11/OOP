package operations;

import exceptions.ArithmeticException;
import exceptions.IllegalArgumentFoundException;
import java.util.Stack;

/**
 * calculate arctg.
 */
public class Arctg extends Operation {

    private Operation expression;

    /**
     * count.
     *
     * @return atan result.
     * @throws ArithmeticException exception.
     */
    @Override
    public double count() throws ArithmeticException {
        return Math.atan(expression.count());
    }

    /**
     * set from stack.
     *
     * @param stack OPeration type.
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
