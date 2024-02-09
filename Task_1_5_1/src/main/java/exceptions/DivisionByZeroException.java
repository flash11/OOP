package exceptions;

/**
 * exception when division by zero occurs.
 */
public class DivisionByZeroException extends ArithmeticException {

    /**
     * main func.
     *
     * @param message for user.
     */
    public DivisionByZeroException(String message) {
        super(message);
    }
}