package fluff.json.deserializer;

import fluff.functions.gen.Func;
import fluff.json.JSONArray;
import fluff.json.JSONException;
import fluff.json.JSONObject;
import fluff.json.converter.JSONConverters;
import fluff.json.deserializer.lexer.JSONLexer;
import fluff.json.deserializer.lexer.JSONToken;
import fluff.json.deserializer.lexer.JSONTokenType;
import fluff.json.deserializer.readers.StringJSONReader;

/**
 * A parser for converting JSON strings into Java objects such as {@link JSONObject} and {@link JSONArray}.
 * It uses a {@link JSONLexer} to tokenize the input and then parses the tokens into appropriate JSON objects.
 */
public class JSONParser {
    
	private final Func<JSONObject> objectFunc;
	private final Func<JSONArray> arrayFunc;
    private final JSONLexer lexer;
    
    private JSONToken token;
    
    /**
     * Constructs a JSONParser with the specified JSON reader.
     *
     * @param objectFunc the function to create a new JSON object
     * @param arrayFunc the function to create a new JSON array
     * @param in the JSON reader to read from
     */
    public JSONParser(Func<JSONObject> objectFunc, Func<JSONArray> arrayFunc, AbstractJSONReader in) {
    	this.objectFunc = objectFunc;
    	this.arrayFunc = arrayFunc;
        this.lexer = new JSONLexer(in);
    }
    
    /**
     * Parses the JSON input and returns the resulting Java object.
     *
     * @return the parsed Java object
     * @throws JSONException if an error occurs while parsing the JSON input
     */
    public Object parse() throws JSONException {
        return parseValue(next());
    }
    
    private Object parseValue(JSONToken token) throws JSONException {
        String value = token.getValue();
        
        return switch (token.getType()) {
        	case NULL -> null;
            case STRING -> JSONConverters.getDeserializer(String.class).deserializeJSON(new StringJSONReader(value));
            case NUMBER -> JSONConverters.getDeserializer(Number.class).deserializeJSON(new StringJSONReader(value));
            case BOOLEAN -> JSONConverters.getDeserializer(Boolean.class).deserializeJSON(new StringJSONReader(value));
            case OPEN_CURLY -> parseObject();
            case OPEN_SQUARE -> parseArray();
            default -> throw new JSONException("Unexpected token: " + token);
        };
    }
    
    private JSONObject parseObject() throws JSONException {
        JSONObject object = objectFunc.invoke();
        
        expect(peek(), JSONTokenType.OPEN_CURLY);
        while (hasNext()) {
            if (next().getType() == JSONTokenType.CLOSE_CURLY) break;
            
            JSONToken key = expect(peek(), JSONTokenType.KEY);
            expect(next(), JSONTokenType.COLON);
            
            Object value = parseValue(next());
            object.put(key.getValue(), value);
            
            if (next().getType() != JSONTokenType.COMMA) break;
        }
        expect(peek(), JSONTokenType.CLOSE_CURLY);
        
        return object;
    }
    
    private JSONArray parseArray() throws JSONException {
        JSONArray array = arrayFunc.invoke();
        
        expect(peek(), JSONTokenType.OPEN_SQUARE);
        while (hasNext()) {
            if (next().getType() == JSONTokenType.CLOSE_SQUARE) break;
            
            Object value = parseValue(peek());
            array.add(value);
            
            if (next().getType() != JSONTokenType.COMMA) break;
        }
        expect(peek(), JSONTokenType.CLOSE_SQUARE);
        
        return array;
    }
    
    /**
     * Checks if there are more tokens to be processed.
     *
     * @return true if there are more tokens, false otherwise
     */
    private boolean hasNext() {
        return token.getType() != JSONTokenType.EOF;
    }
    
    /**
     * Returns the current token without advancing the lexer.
     *
     * @return the current token
     */
    private JSONToken peek() {
        return token;
    }
    
    /**
     * Advances the lexer to the next token.
     *
     * @return the next token
     * @throws JSONException if an error occurs while reading the next token
     */
    private JSONToken next() throws JSONException {
        return token = lexer.nextToken();
    }
    
    /**
     * Expects the token to be of the specified type.
     *
     * @param token the token
     * @param type the expected token type
     * @return the current token if it matches the expected type
     * @throws JSONException if the current token does not match the expected type
     */
    private JSONToken expect(JSONToken token, JSONTokenType type) throws JSONException {
        if (!type.equals(token.getType())) throw new JSONException("Expected token type " + type + " but found " + token.getType());
        return token;
    }
}
