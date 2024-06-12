package fluff.json.serializer.writers;

import fluff.json.JSONConfig;
import fluff.json.deserializer.lexer.JSONTokenType;
import fluff.json.serializer.AbstractJSONWriter;

public class PrettyJSONWriter extends AbstractJSONWriter {
	
	protected int tabs = 0;
	protected boolean marked = false;
	
	@Override
	public void token(JSONTokenType type) {
		switch (type) {
			case OPEN_CURLY:
			case OPEN_SQUARE:
				write(type.getChar());
				tabs++;
				newLine();
				marked = true;
				break;
			case CLOSE_CURLY:
			case CLOSE_SQUARE:
				newLine();
				tabs--;
				writeTabs();
				write(type.getChar());
				break;
			case COMMA:
				write(JSONConfig.PRETTY_COMMA);
				newLine();
				marked = true;
				break;
			case COLON:
				write(JSONConfig.PRETTY_COLON);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}
	
	@Override
	public void write(Object value) {
		if (marked) {
			marked = false;
			writeTabs();
		}
		super.write(value);
	}
	
	protected void writeTabs() {
		for (int i = 0; i < tabs; i++) {
			write(JSONConfig.PRETTY_TAB);
		}
	}
	
	protected void newLine() {
		write(JSONConfig.PRETTY_NEW_LINE);
	}
}
