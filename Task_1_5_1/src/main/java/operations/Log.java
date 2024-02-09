package operations;

import exceptions.ArithmeticException;
import exceptions.IllegalArgumentFoundException;
import exceptions.LogarithmIncorrectArgumentException;
import java.util.Stack;

/**
 * log.
 */
public class Log extends Operation {

    private Operation expression;

    /**
     * count.
     *
     * @return log output.
     * @throws ArithmeticException exception.
     */
    @Override
    public double count() throws ArithmeticException {
        double expr = expression.count();
        if (expr <= 0){
            throw new LogarithmIncorrectArgumentException("Аргумент должен быть больше нуля!");
        }
        return Math.log(expr);
    }

    /**
     * set from stack.
     *
     * @param stack Operation type.
     * @throws IllegalArgumentFoundException exception.
     */
    @Override
    public void setArgsFromStack(Stack<Operation> stack) throws IllegalArgumentFoundException {
        if (stack.size() < 1) {
            throw new IllegalArgumentFoundException("Недостаточное количество аргументов!");
        }
        expression = stack.pop();

    }
}
