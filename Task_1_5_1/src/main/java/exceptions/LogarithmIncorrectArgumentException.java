package exceptions;

/**
 * exception when incorrect number of logarithm.
 */
public class LogarithmIncorrectArgumentException extends ArithmeticException {

    /**
     * main func.
     *
     * @param message for user.
     */
    public LogarithmIncorrectArgumentException(String message) {
        super(message);
    }
}