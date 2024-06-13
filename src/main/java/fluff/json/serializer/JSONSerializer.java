package fluff.json.serializer;

/**
 * An interface for serializing Java objects to JSON format.
 *
 * @param <V> the type of the object to be serialized
 */
public interface JSONSerializer<V> {
    
    /**
     * Serializes the specified value to JSON format using the provided writer.
     *
     * @param value the value to be serialized
     * @param out the writer to use for serializing the value
     */
    void serializeJSON(V value, AbstractJSONWriter out);
}
