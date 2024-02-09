package operations;

import exceptions.ArithmeticException;
import exceptions.IllegalArgumentFoundException;
import java.util.Stack;

/**
 * abstact class.
 */
public abstract class Operation {

    /**
     * recursive method which calculate expressions.
     *
     * @return double.
     * @throws ArithmeticException exception.
     */
    public abstract double count() throws ArithmeticException;

    /**
     * setter.
     *
     * @param stack.
     * @throws IllegalArgumentFoundException exception.
     */
    public abstract void setArgsFromStack(Stack<Operation> stack) 
        throws IllegalArgumentFoundException;

}
