package fluff.json.deserializer;

import fluff.json.JSON;
import fluff.json.JSONArray;
import fluff.json.JSONException;
import fluff.json.JSONObject;
import fluff.json.converter.JSONConverters;
import fluff.json.deserializer.lexer.JSONLexer;
import fluff.json.deserializer.lexer.JSONToken;
import fluff.json.deserializer.lexer.JSONTokenType;
import fluff.json.deserializer.readers.StringJSONReader;

public class JSONParser {
	
	private final JSONLexer lexer;
	
	private JSONToken token;
	
	public JSONParser(AbstractJSONReader in) {
		this.lexer = new JSONLexer(in);
	}
	
	public Object parse() throws JSONException {
		return parseValue(next());
	}
	
	private Object parseValue(JSONToken token) throws JSONException {
		String value = token.getValue();
		
		return switch (token.getType()) {
			case STRING -> JSONConverters.getDeserializer(String.class).deserializeJSON(new StringJSONReader(value));
			case NUMBER -> JSONConverters.getDeserializer(Number.class).deserializeJSON(new StringJSONReader(value));
			case BOOLEAN -> JSONConverters.getDeserializer(Boolean.class).deserializeJSON(new StringJSONReader(value));
			case NULL -> null;
			case OPEN_CURLY -> parseObject();
			case OPEN_SQUARE -> parseArray();
			default -> throw new JSONException("Unexpected token: " + token);
		};
	}
	
	private JSONObject parseObject() throws JSONException {
		JSONObject object = JSON.object();
		
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
		JSONArray array = JSON.array();
		
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
	
	private boolean hasNext() {
		return token.getType() != JSONTokenType.EOF;
	}
	
	private JSONToken peek() {
		return token;
	}
	
	private JSONToken next() throws JSONException {
		return token = lexer.nextToken();
	}
	
	private JSONToken expect(JSONToken token, JSONTokenType type) throws JSONException {
		if (!type.equals(token.getType())) throw new JSONException("Expected token type " + type + " but found " + token.getType());
		return token;
	}
}
