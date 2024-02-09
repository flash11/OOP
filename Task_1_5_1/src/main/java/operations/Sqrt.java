package operations;

import exceptions.IllegalArgumentFoundException;
import exceptions.SquareRootLessThanZeroException;
import exceptions.ArithmeticException;
import java.util.Stack;

/**
 * sqrt.
 */
public class Sqrt extends Operation {

    private Operation expression;

    /**
     * count.
     *
     * @return expression.
     * @throws ArithmeticException exception.
     */
    @Override
    public double count() throws ArithmeticException {
        double expr = expression.count();
        if (expr < 0){
            throw new SquareRootLessThanZeroException("Нельзя на ноль делить!");
        }
        return Math.sqrt(expr);
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
