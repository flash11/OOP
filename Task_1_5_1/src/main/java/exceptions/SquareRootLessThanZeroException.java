package exceptions;

/**
 * exception when incorrect square root
 */
public class SquareRootLessThanZeroException extends Throwable{
    /**
     * main func
     * @param message - for user
     */

    public SquareRootLessThanZeroException(String message) {
        super(message);

    }

}