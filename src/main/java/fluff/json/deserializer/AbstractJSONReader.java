package fluff.json.deserializer;

/**
 * An abstract class representing a JSON reader that reads JSON input as a sequence of characters.
 */
public abstract class AbstractJSONReader {
    
    protected int current = 0;
    
    /**
     * Returns the entire text of the JSON input.
     *
     * @return the JSON input as a string
     */
    public abstract String text();
    
    /**
     * Returns the character at the specified index.
     *
     * @param index the index of the character to return
     * @return the character at the specified index
     */
    public abstract char get(int index);
    
    /**
     * Returns the size of the JSON input.
     *
     * @return the size of the JSON input
     */
    public abstract int size();
    
    /**
     * Returns the number of remaining characters to read.
     *
     * @return the number of remaining characters
     */
    public int remaining() {
        return size() - current() - 1;
    }
    
    /**
     * Returns the current position in the JSON input.
     *
     * @return the current position
     */
    public int current() {
        return current;
    }
    
    /**
     * Checks if there are at least the specified number of characters remaining to read.
     *
     * @param size the number of characters to check for
     * @return true if there are at least the specified number of characters remaining, false otherwise
     */
    public boolean hasNext(int size) {
        return remaining() >= size;
    }
    
    /**
     * Checks if there is at least one character remaining to read.
     *
     * @return true if there is at least one character remaining, false otherwise
     */
    public boolean hasNext() {
        return hasNext(1);
    }
    
    /**
     * Advances to the next character and returns it.
     *
     * @return the next character
     */
    public char next() {
        return get(set(current() + 1));
    }
    
    /**
     * Moves back to the previous character and returns it.
     *
     * @return the previous character
     */
    public char back() {
        return get(set(current() - 1));
    }
    
    /**
     * Returns the current character without advancing the position.
     *
     * @return the current character
     */
    public char peek() {
        return get(current());
    }
    
    /**
     * Sets the current position to the specified index.
     *
     * @param index the index to set the current position to
     * @return the new current position
     */
    public int set(int index) {
        current = index;
        return current;
    }
}
