package fluff.json.deserializer;

/**
 * An interface for deserializing JSON input into Java objects.
 *
 * @param <V> the type of the object that this deserializer produces
 */
public interface JSONDeserializer<V> {
    
    /**
     * Deserializes JSON input into an object of type {@code V}.
     *
     * @param in the JSON reader used to read the JSON input
     * @return the deserialized object of type {@code V}
     */
    V deserializeJSON(AbstractJSONReader in);
}
