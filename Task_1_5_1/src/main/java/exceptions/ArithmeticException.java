package exceptions;

/**
 * Exception parent-class for arithmetic.
 */
public class ArithmeticException extends Throwable {

    /**
     * main func.
     *
     * @param message for user.
     */
    public ArithmeticException(String message) {
        super(message);
    }
}