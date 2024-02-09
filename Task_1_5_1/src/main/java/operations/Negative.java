package operations;

import exceptions.ArithmeticException;
import exceptions.IllegalArgumentFoundException;
import java.util.Stack;

/**
 * negative number.
 */
public class Negative extends Operation {

    private Operation expression;

    /**
     * count.
     *
     * @return expression.
     * @throws ArithmeticException.
     */
    @Override
    public double count() throws ArithmeticException {
        return expression.count()*(-1);
    }

    /**
     * set from stack.
     *
     * @param stack.
     * @throws IllegalArgumentFoundException.
     */
    @Override
    public void setArgsFromStack(Stack<Operation> stack) throws IllegalArgumentFoundException {
        if (stack.size() < 1){
            throw new IllegalArgumentFoundException("");
        }
        expression = stack.pop();
    }
}
