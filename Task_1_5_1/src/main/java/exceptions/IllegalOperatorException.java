package exceptions;

/**
 * exception when incorrect operator.
 */
public class IllegalOperatorException extends Throwable{

    /**
     * main func.
     *
     * @param message for user.
     */
    public IllegalOperatorException(String message) {
        super(message);
    }

}