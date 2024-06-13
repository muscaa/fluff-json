package fluff.json;

/**
 * A custom exception class for handling JSON-related errors.
 */
public class JSONException extends Exception {
    
    private static final long serialVersionUID = -5924198373571432499L;
    
    /**
     * Constructs a new JSONException with {@code null} as its detail message.
     */
    public JSONException() {
        super();
    }
    
    /**
     * Constructs a new JSONException with the specified detail message.
     *
     * @param message the detail message
     */
    public JSONException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new JSONException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public JSONException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new JSONException with the specified cause.
     *
     * @param cause the cause
     */
    public JSONException(Throwable cause) {
        super(cause);
    }
}
