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
     * @param stack Operation type.
     * @throws IllegalArgumentFoundException exception.
     */
    @Override
    public void setArgsFromStack(Stack<Operation> stack) throws IllegalArgumentFoundException {}

    /**
     * count.
     *
     * @return a1 Number.
     */
    @Override
    public double count() {
        return a1;
    }

    /**
     * constructor.
     *
     * @param num Douple type.
     */
    public Num(double num){
        this.a1 = num;
    }
}
