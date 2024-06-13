package fluff.json.deserializer.lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Enum representing the different types of tokens that can be encountered while parsing JSON.
 */
public enum JSONTokenType {
    SOF(),
    
    OPEN_CURLY('{'),
    CLOSE_CURLY('}'),
    OPEN_SQUARE('['),
    CLOSE_SQUARE(']'),
    
    KEY(),
    VALUE(),
    
    COLON(':'),
    COMMA(','),
    
    SPACE(' '),
    TAB('\t'),
    NEW_LINE('\n'),
    CARRIAGE_RETURN('\r'),
    
    EOF(),
    
    NULL(),
    STRING(),
    NUMBER(),
    BOOLEAN(),
    ;
	
    private static final Map<Character, JSONTokenType> REG = new HashMap<>();
    
    static {
        for (JSONTokenType token : values()) {
            if (token.character != 0) REG.put(token.character, token);
            
            for (JSONTokenType next : values()) {
                token.results.put(next, JSONResult.INVALID);
            }
        }
        
        KEY.extend(STRING);
        VALUE.extend(NULL, STRING, NUMBER, BOOLEAN, OPEN_CURLY, CLOSE_CURLY, OPEN_SQUARE, CLOSE_SQUARE);
        
        SOF.put(JSONResult.IGNORE, SPACE, TAB, NEW_LINE, CARRIAGE_RETURN);
        SOF.put(JSONResult.VALID, OPEN_CURLY, OPEN_SQUARE);
        
        OPEN_CURLY.put(JSONResult.IGNORE, SPACE, TAB, NEW_LINE, CARRIAGE_RETURN);
        OPEN_CURLY.put(JSONResult.VALID, CLOSE_CURLY, KEY);
        
        CLOSE_CURLY.put(JSONResult.IGNORE, SPACE, TAB, NEW_LINE, CARRIAGE_RETURN);
        CLOSE_CURLY.put(JSONResult.VALID, EOF, COMMA);
        
        OPEN_SQUARE.put(JSONResult.IGNORE, SPACE, TAB, NEW_LINE, CARRIAGE_RETURN);
        OPEN_SQUARE.put(JSONResult.VALID, CLOSE_SQUARE, VALUE);
        
        CLOSE_SQUARE.put(JSONResult.IGNORE, SPACE, TAB, NEW_LINE, CARRIAGE_RETURN);
        CLOSE_SQUARE.put(JSONResult.VALID, EOF, COMMA);
        
        KEY.put(JSONResult.IGNORE, SPACE, TAB, NEW_LINE, CARRIAGE_RETURN);
        KEY.put(JSONResult.VALID, COLON);
        
        VALUE.put(JSONResult.IGNORE, SPACE, TAB, NEW_LINE, CARRIAGE_RETURN);
        VALUE.put(JSONResult.VALID, COMMA, CLOSE_CURLY, CLOSE_SQUARE);
        
        COLON.put(JSONResult.IGNORE, SPACE, TAB, NEW_LINE, CARRIAGE_RETURN);
        COLON.put(JSONResult.VALID, VALUE);
        
        COMMA.put(JSONResult.IGNORE, SPACE, TAB, NEW_LINE, CARRIAGE_RETURN);
        COMMA.put(JSONResult.VALID, KEY, VALUE);
    }
    
    private final Map<JSONTokenType, JSONResult> results = new HashMap<>();
    private final List<JSONTokenType> extend = new ArrayList<>();
    
    private final char character;
    
    private JSONTokenType(char character) {
        this.character = character;
    }
    
    private JSONTokenType() {
        this((char) 0);
    }
    
    /**
     * Determines the expected result for the next token type.
     *
     * @param next the next token type
     * @return the expected result for the next token type
     */
    public JSONResult expects(JSONTokenType next) {
        if (next == null) return JSONResult.INVALID;
        
        return results.get(next);
    }
    
    /**
     * Checks if the current token type is equal to the specified token type or any of its extended types.
     *
     * @param type the token type to compare
     * @return true if the current token type is equal to the specified token type or any of its extended types, false otherwise
     */
    public boolean equals(JSONTokenType type) {
        if (this == type) return true;
        
        for (JSONTokenType e : extend) {
            if (e == type) return true;
        }
        
        return false;
    }
    
    /**
     * Returns the character associated with the token type.
     *
     * @return the character associated with the token type
     */
    public char getChar() {
        return character;
    }
    
    /**
     * Extends the current token type with additional token types.
     *
     * @param tokens the token types to extend with
     */
    private void extend(JSONTokenType... tokens) {
        for (JSONTokenType token : tokens) {
            extend.add(token);
        }
    }
    
    /**
     * Puts the specified result for the given token types.
     *
     * @param result the result to put
     * @param tokens the token types to put the result for
     */
    private void put(JSONResult result, JSONTokenType... tokens) {
        if (extend.isEmpty()) put(this, result, tokens);
        
        for (JSONTokenType e : extend) {
            put(e, result, tokens);
        }
    }
    
    /**
     * Puts the specified result for the given token types into the results map of the specified token type.
     *
     * @param token the token type to put the results for
     * @param result the result to put
     * @param tokens the token types to put the result for
     */
    private void put(JSONTokenType token, JSONResult result, JSONTokenType[] tokens) {
        for (JSONTokenType next : tokens) {
            if (next.extend.isEmpty()) token.results.put(next, result);
            
            for (JSONTokenType e : next.extend) {
                token.results.put(e, result);
            }
        }
    }
    
    /**
     * Returns the token type associated with the specified character.
     *
     * @param c the character to get the token type for
     * @return the token type associated with the specified character, or null if not found
     */
    public static JSONTokenType get(char c) {
        return REG.get(c);
    }
}
