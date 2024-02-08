package exceptions;

/**
 * exception when incorrect square root.
 */
public class SquareRootLessThanZeroException extends ArithmeticException{

    /**
     * main func.
     *
     * @param message for user.
     */
    public SquareRootLessThanZeroException(String message) {
        super(message);
    }
}