package operations;

import exceptions.ArithmeticException;
import exceptions.DivisionByZeroException;
import exceptions.IllegalArgumentFoundException;
import java.util.Stack;

/**
 * division.
 */
public class Division extends Operation {

    private Operation expr1;
    private Operation expr2;


    /**
     * count.
     *
     * @return divided arg.
     * @throws ArithmeticException exception.
     */
    @Override
    public double count() throws ArithmeticException {
        double expr = expr2.count();
        if (expr == 0){
            throw new DivisionByZeroException("Нельзя делить на ноль!");
        }
        return expr1.count() / expr2.count();
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
            throw new IllegalArgumentFoundException("Недостаточное количество аргументов");
        }

        expr1 = stack.pop();
        expr2 = stack.pop();

    }
}
