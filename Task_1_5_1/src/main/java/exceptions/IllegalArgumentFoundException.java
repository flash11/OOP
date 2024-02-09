package exceptions;

/**
 * exception when incorrect operator.
 */
public class IllegalArgumentFoundException extends Throwable {

    /**
     * main func.
     *
     * @param message for user.
     */
    public IllegalArgumentFoundException(String message) {
        super(message);
    }
}
