package fluff.json.deserializer.readers;

import fluff.json.deserializer.AbstractJSONReader;

/**
 * A JSON reader that reads JSON input from a string.
 */
public class StringJSONReader extends AbstractJSONReader {
    
    protected final String text;
    
    /**
     * Constructs a new StringJSONReader with the specified JSON input.
     *
     * @param text the JSON input as a string
     */
    public StringJSONReader(String text) {
        this.text = text;
    }
    
    @Override
    public String text() {
        return text;
    }
    
    @Override
    public char get(int index) {
        return text.charAt(index);
    }
    
    @Override
    public int size() {
        return text.length();
    }
}
