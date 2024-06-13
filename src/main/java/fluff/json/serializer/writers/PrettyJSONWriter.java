package fluff.json.serializer.writers;

import fluff.json.JSONConfig;
import fluff.json.deserializer.lexer.JSONTokenType;
import fluff.json.serializer.AbstractJSONWriter;

/**
 * A JSON writer that produces formatted, human-readable JSON output.
 * This writer adds indentation, newlines, and spaces to enhance the readability of the JSON output.
 */
public class PrettyJSONWriter extends AbstractJSONWriter {
    
    protected int tabs = 0;
    protected boolean newLine = false;
    
    @Override
    public void token(JSONTokenType type) {
        switch (type) {
            case OPEN_CURLY:
            case OPEN_SQUARE:
                write(type.getChar());
                tabs++;
                newLine();
                newLine = true;
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
                newLine = true;
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
        if (newLine) {
            newLine = false;
            writeTabs();
        }
        super.write(value);
    }
    
    /**
     * Writes the current level of indentation.
     */
    protected void writeTabs() {
        for (int i = 0; i < tabs; i++) {
            write(JSONConfig.PRETTY_TAB);
        }
    }
    
    /**
     * Writes a newline character to the output.
     */
    protected void newLine() {
        write(JSONConfig.PRETTY_NEW_LINE);
    }
}
