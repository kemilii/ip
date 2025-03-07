package moli;

/**
 * A custom exception class for handling errors in the Moli chatbot.
 */
public class MoliException extends Exception {

    /**
     * Constructs a new MoliException with the specified error message.
     *
     * @param message The error message.
     */
    public MoliException(String message) {
        super(message);
    }
}
