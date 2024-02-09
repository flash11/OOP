package operations;

import exceptions.IllegalArgumentFoundException;
import java.util.Stack;

/**
 * usual number.
 */
public class Num extends Operation {

    private double a1;

    /**
     * set from stack to expr.
     *
     * @param stack.
     * @throws IllegalArgumentFoundException exception.
     */
    @Override
    public void setArgsFromStack(Stack<Operation> stack) throws IllegalArgumentFoundException {}

    /**
     * count.
     *
     * @return a1.
     */
    @Override
    public double count() {
        return a1;
    }

    /**
     * constructor.
     *
     * @param num.
     */
    public Num(double num){
        this.a1 = num;
    }
}
