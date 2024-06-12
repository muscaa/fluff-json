package fluff.json.serializer.writers;

import fluff.json.deserializer.lexer.JSONTokenType;
import fluff.json.serializer.AbstractJSONWriter;

public class InlineJSONWriter extends AbstractJSONWriter {
	
	@Override
	public void token(JSONTokenType type) {
		write(type.getChar());
	}
}
