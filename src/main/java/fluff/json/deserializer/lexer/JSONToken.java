package fluff.json.deserializer.lexer;

public class JSONToken {
	
	private final JSONTokenType type;
	private final String value;
	
	public JSONToken(JSONTokenType type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public JSONToken(JSONTokenType type, char value) {
		this(type, String.valueOf(value));
	}
	
	public JSONToken(JSONTokenType type) {
		this(type, null);
	}
	
	public JSONTokenType getType() {
		return type;
	}
	
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
