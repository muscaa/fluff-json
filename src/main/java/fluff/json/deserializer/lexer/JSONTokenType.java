package fluff.json.deserializer.lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	STRING(),
	NUMBER(),
	BOOLEAN(),
	NULL(),
	;
	
	private static final Map<Character, JSONTokenType> REG = new HashMap<>();
	
	static {
		for (JSONTokenType token : values()) {
			for (char c : token.chars) {
				REG.put(c, token);
			}
			
			for (JSONTokenType next : values()) {
				token.results.put(next, JSONResult.INVALID);
			}
		}
		
		KEY.extend(STRING);
		VALUE.extend(STRING, NUMBER, BOOLEAN, NULL, OPEN_CURLY, CLOSE_CURLY, OPEN_SQUARE, CLOSE_SQUARE);
		
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
	
	private final char[] chars;
	
	private JSONTokenType(char... chars) {
		this.chars = chars;
	}
	
	public JSONResult expects(JSONTokenType next) {
		if (next == null) return JSONResult.INVALID;
		
		return results.get(next);
	}
	
	public boolean equals(JSONTokenType type) {
		if (this == type) return true;
		
		for (JSONTokenType e : extend) {
			if (e == type) return true;
		}
		
		return false;
	}
	
	private void extend(JSONTokenType... tokens) {
		for (JSONTokenType token : tokens) {
			extend.add(token);
		}
	}
	
	private void put(JSONResult result, JSONTokenType... tokens) {
		if (extend.isEmpty()) put(this, result, tokens);
		
		for (JSONTokenType e : extend) {
			put(e, result, tokens);
		}
	}
	
	private void put(JSONTokenType token, JSONResult result, JSONTokenType[] tokens) {
		for (JSONTokenType next : tokens) {
			if (next.extend.isEmpty()) token.results.put(next, result);
			
			for (JSONTokenType e : next.extend) {
				token.results.put(e, result);
			}
		}
	}
	
	public static JSONTokenType get(char c) {
		return REG.get(c);
	}
}
