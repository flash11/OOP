package operations;

import exceptions.ArithmeticException;
import exceptions.IllegalArgumentFoundException;
import java.util.Stack;

/**
 * minus.
 */
public class Minus extends Operation {

    private Operation expr1;
    private Operation expr2;

    /**
     * count.
     *
     * @return result expression.
     * @throws ArithmeticException exception.
     */
    @Override
    public double count() throws ArithmeticException {
        return expr1.count() - expr2.count();
    }

    /**
     * set from stack.
     *
     * @param stack Operation type.
     * @throws IllegalArgumentFoundException exception.
     */
    @Override
    public void setArgsFromStack(Stack<Operation> stack) throws IllegalArgumentFoundException {
        if (stack.size() < 2) {
            throw new IllegalArgumentFoundException("");
        }
        expr1 = stack.pop();
        expr2 = stack.pop();
    }
}
