package operations;

import exceptions.ArithmeticException;
import exceptions.IllegalArgumentFoundException;
import java.util.Stack;

/**
 * miltiply.
 */
public class Multiplication extends Operation {

    private Operation expr1;
    private Operation expr2;

    /**
     * count.
     *
     * @return multiplication.
     * @throws ArithmeticException.
     */
    @Override
    public double count() throws ArithmeticException {
        return expr1.count() * expr2.count();
    }

    /**
     * set from stack.
     *
     * @param stack.
     * @throws IllegalArgumentFoundException.
     */
    @Override
    public void setArgsFromStack(Stack<Operation> stack) throws IllegalArgumentFoundException {
        if (stack.size() < 2) {
            throw new IllegalArgumentFoundException("Недостаточное количество аргументов");
        }
        expr1 = stack.pop();
        expr2 = stack.pop();
    }
}
