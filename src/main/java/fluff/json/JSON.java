package fluff.json;

import fluff.json.deserializer.readers.StringJSONReader;
import fluff.json.serializer.writers.InlineJSONWriter;
import fluff.json.serializer.writers.PrettyJSONWriter;
import fluff.json.utils.JSONUtils;

/**
 * An abstract class representing a JSON object or array, providing utility methods
 * for serialization and deserialization.
 */
public abstract class JSON {
    
    /**
     * Serializes the current JSON object to a string using the {@link InlineJSONWriter}.
     *
     * @return a string representation of the JSON object
     */
    @Override
    public String toString() {
        return JSONUtils.serialize(this, new InlineJSONWriter()).getResult();
    }
    
    /**
     * Serializes the current JSON object to a pretty-printed string using the {@link PrettyJSONWriter}.
     *
     * @return a pretty-printed string representation of the JSON object
     */
    public String toPrettyString() {
        return JSONUtils.serialize(this, new PrettyJSONWriter()).getResult();
    }
    
    /**
     * Creates a new {@link JSONObject}.
     *
     * @return a new JSONObject
     */
    public static JSONObject object() {
        return new JSONObject();
    }
    
    /**
     * Parses a JSON string into a {@link JSONObject}.
     *
     * @param json the JSON string to parse
     * @return the parsed JSONObject
     */
    public static JSONObject object(String json) {
        return JSONUtils.deserialize(new StringJSONReader(json));
    }
    
    /**
     * Creates a new {@link JSONArray}.
     *
     * @return a new JSONArray
     */
    public static JSONArray array() {
        return new JSONArray();
    }
    
    /**
     * Parses a JSON string into a {@link JSONArray}.
     *
     * @param json the JSON string to parse
     * @return the parsed JSONArray
     */
    public static JSONArray array(String json) {
        return JSONUtils.deserialize(new StringJSONReader(json));
    }
}
