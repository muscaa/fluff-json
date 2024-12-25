package fluff.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import fluff.functions.gen.Func;
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
     * Creates a new {@link JSONObject} with a hash map.
     *
     * @return a new JSONObject with a hash map
     */
    public static JSONObject object() {
        return new JSONObject(new HashMap<>());
    }
    
	/**
	 * Creates a new {@link JSONObject} with a linked hash map.
	 *
	 * @return a new JSONObject with a linked hash map
	 */
    public static JSONObject linkedObject() {
        return new JSONObject(new LinkedHashMap<>());
    }
    
    /**
     * Parses a JSON string into a {@link JSONObject}.
     *
     * @param objectFunc the function to create a new JSON object
     * @param arrayFunc the function to create a new JSON array
     * @param json the JSON string to parse
     * @return the parsed JSONObject
     */
    public static JSONObject object(Func<JSONObject> objectFunc, Func<JSONArray> arrayFunc, String json) {
        return JSONUtils.deserialize(objectFunc, arrayFunc, new StringJSONReader(json));
    }
    
    /**
     * Parses a JSON string into a {@link JSONObject}.
     *
     * @param json the JSON string to parse
     * @return the parsed JSONObject
     */
    public static JSONObject object(String json) {
        return object(JSON::object, JSON::array, json);
    }
    
    /**
     * Creates a new {@link JSONArray} with an array list.
     *
     * @return a new JSONArray with an array list
     */
    public static JSONArray array() {
        return new JSONArray(new ArrayList<>());
    }
    
	/**
	 * Creates a new {@link JSONArray} with a linked list.
	 *
	 * @return a new JSONArray with a linked list
	 */
	public static JSONArray linkedArray() {
		return new JSONArray(new LinkedList<>());
	}
    
    /**
     * Parses a JSON string into a {@link JSONArray}.
     *
     * @param objectFunc the function to create a new JSON object
     * @param arrayFunc the function to create a new JSON array
     * @param json the JSON string to parse
     * @return the parsed JSONArray
     */
    public static JSONArray array(Func<JSONObject> objectFunc, Func<JSONArray> arrayFunc, String json) {
        return JSONUtils.deserialize(objectFunc, arrayFunc, new StringJSONReader(json));
    }
    
    /**
     * Parses a JSON string into a {@link JSONArray}.
     *
     * @param json the JSON string to parse
     * @return the parsed JSONArray
     */
    public static JSONArray array(String json) {
        return array(JSON::object, JSON::array, json);
    }
}
