package fluff.json.deserializer.lexer;

import java.util.ArrayList;
import java.util.List;

import fluff.json.JSONException;
import fluff.json.deserializer.AbstractJSONReader;
import fluff.json.utils.JSONUtils;

public class JSONLexer {
	
	private final AbstractJSONReader in;
	
	private JSONTokenType last = JSONTokenType.SOF;
	
	private final List<JSONToken> tokens = new ArrayList<>();
	
	public JSONLexer(AbstractJSONReader in) {
		this.in = in;
		
		in.set(-1);
	}
	
	public JSONToken nextToken() throws JSONException {
		JSONTokenType type;
		do {
			type = in.hasNext() ? JSONTokenType.get(in.next()) : JSONTokenType.EOF;
		} while (last.expects(type) == JSONResult.IGNORE);
		
		if (type == JSONTokenType.EOF) {
			if (last.expects(type) == JSONResult.INVALID) throw new JSONException("Unexpected EOF!");
			
			return new JSONToken(type);
		}
		
		char peek = in.peek();
		String value = null;
		
		if (type == null) {
			
			if (peek == '"') {
				value = readText();
				
				type = JSONTokenType.STRING;
			} else if (JSONUtils.isDigit(peek) || peek == '-') {
				value = readNumber();
				
				type = JSONTokenType.NUMBER;
			} else if (JSONUtils.isAlpha(peek)) {
				value = readString().toLowerCase();
				
				type = switch (value) {
					case "true" -> JSONTokenType.BOOLEAN;
					case "false" -> JSONTokenType.BOOLEAN;
					case "null" -> JSONTokenType.NULL;
					default -> throw new JSONException("Unexpected value: " + value);
				};
			} else {
				throw new JSONException("Unexpected character: " + peek);
			}
		}
		
		if (last.expects(type) == JSONResult.INVALID) throw new JSONException("Unexpected token type: " + type, new JSONException(tokens + ""));
		
		last = type;
		JSONToken token = value == null ? new JSONToken(type, peek) : new JSONToken(type, value);
		tokens.add(token);
		return token;
	}
	
	public List<JSONToken> tokenize() throws JSONException {
		List<JSONToken> list = new ArrayList<>();
		
		JSONToken token;
		while ((token = nextToken()).getType() != JSONTokenType.EOF) {
			list.add(token);
		}
		
		return list;
	}
	
    private String readText() throws JSONException {
        StringBuilder sb = new StringBuilder();
        
        in.next(); // consume "
        for (char c = in.peek(); in.hasNext(); c = in.next()) {
        	if (c == '"') break;
        	
        	if (c != '\\') {
        		sb.append(c);
        		continue;
        	}
        	
        	in.next(); // consume \
        	if (!in.hasNext()) throw new JSONException("Empty escape sequence");
        	
			char escape = switch (in.peek()) {
				case '"' -> '"';
				case '\\' -> '\\';
				case '/' -> '/';
				case 'b' -> '\b';
				case 'f' -> '\f';
				case 'n' -> '\n';
				case 'r' -> '\r';
				case 't' -> '\t';
				default -> 0;
			};
			
			if (escape != 0) {
				sb.append(escape);
				continue;
			}
			
			if (in.peek() != 'u') throw new JSONException("Invalid escape sequence: \\" + in.peek());
			in.next(); // consume u
			
			if (!in.hasNext(4)) throw new JSONException("Invalid unicode escape sequence");
			
			StringBuilder hex = new StringBuilder();
			hex.append(in.next());
			hex.append(in.next());
			hex.append(in.next());
			hex.append(in.next());
			
			sb.append(Integer.parseInt(hex.toString(), 16));
        }
                
        return sb.toString();
    }
	
    private String readNumber() {
        StringBuilder sb = new StringBuilder();
        
        if (in.peek() == '-') {
        	sb.append('-');
        	in.next(); // consume -
        }
        
        int dotCount = 0;
        for (char c = in.peek(); in.hasNext(); c = in.next()) {
        	if (c == '.') {
        		if (++dotCount > 1) break;
        		
        		sb.append(".");
        		continue;
        	}
        	
        	if (!JSONUtils.isDigit(c)) break;
        	
        	sb.append(c);
        }
        in.back(); // recheck char
        
        return sb.toString();
    }

    private String readString() {
        StringBuilder sb = new StringBuilder();
        
        for (char c = in.peek(); in.hasNext(); c = in.next()) {
        	if (!JSONUtils.isAlpha(c)) break;
        	
        	sb.append(c);
        }
        in.back(); // recheck char
        
        return sb.toString();
    }
}
