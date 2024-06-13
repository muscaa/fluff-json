package fluff.json.serializer;

import fluff.json.deserializer.lexer.JSONTokenType;
import fluff.json.utils.JSONUtils;

/**
 * An abstract base class for writing JSON data. Provides basic methods for writing values and tokens.
 */
public abstract class AbstractJSONWriter {
    
    /**
     * The StringBuilder used for constructing the JSON output.
     */
    protected final StringBuilder out = new StringBuilder();
    
    /**
     * Writes a JSON token of the specified type.
     *
     * @param type the type of JSON token to write
     */
    public abstract void token(JSONTokenType type);
    
    /**
     * Writes the specified value to the JSON output.
     *
     * @param value the value to write
     */
    public void write(Object value) {
        out.append(value);
    }
    
    /**
     * Writes the specified value to the JSON output, enclosing it in quotes.
     *
     * @param value the value to write
     */
    public void writeQuoted(Object value) {
        write(JSONUtils.quote(value));
    }
    
    /**
     * Returns the current JSON output as a trimmed string.
     *
     * @return the current JSON output
     */
    public String getResult() {
        return out.toString().trim();
    }
}
