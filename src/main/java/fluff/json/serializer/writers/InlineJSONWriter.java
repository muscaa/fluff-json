package fluff.json.serializer.writers;

import fluff.json.deserializer.lexer.JSONTokenType;
import fluff.json.serializer.AbstractJSONWriter;

/**
 * A JSON writer that produces compact JSON output by writing all tokens and values inline without additional formatting.
 */
public class InlineJSONWriter extends AbstractJSONWriter {
    
    @Override
    public void token(JSONTokenType type) {
        write(type.getChar());
    }
}
