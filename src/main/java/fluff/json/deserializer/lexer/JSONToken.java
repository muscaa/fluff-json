package fluff.json.deserializer.lexer;

/**
 * Represents a JSON token with a specific type and value.
 */
public class JSONToken {
    
    private final JSONTokenType type;
    private final String value;
    
    /**
     * Constructs a JSONToken with the specified type and value.
     *
     * @param type the type of the JSON token
     * @param value the value of the JSON token
     */
    public JSONToken(JSONTokenType type, String value) {
        this.type = type;
        this.value = value;
    }
    
    /**
     * Constructs a JSONToken with the specified type and character value.
     *
     * @param type the type of the JSON token
     * @param value the character value of the JSON token
     */
    public JSONToken(JSONTokenType type, char value) {
        this(type, String.valueOf(value));
    }
    
    /**
     * Constructs a JSONToken with the specified type and a null value.
     *
     * @param type the type of the JSON token
     */
    public JSONToken(JSONTokenType type) {
        this(type, null);
    }
    
    /**
     * Gets the type of the JSON token.
     *
     * @return the type of the JSON token
     */
    public JSONTokenType getType() {
        return type;
    }
    
    /**
     * Gets the value of the JSON token.
     *
     * @return the value of the JSON token, or null if it does not have a value
     */
    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return "JSONToken{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
