package fluff.json;

import java.util.HashMap;
import java.util.Map;

import fluff.json.deserializer.JSONDeserializer;
import fluff.json.deserializer.readers.StringJSONReader;
import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;
import fluff.json.serializer.writers.InlineJSONWriter;

/**
 * Represents a JSON object, which is a collection of key-value pairs.
 * Extends the {@link JSON} class.
 */
public class JSONObject extends JSON {
    
    private final Map<String, Object> map = new HashMap<>();
    
    /**
     * Retrieves the value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @param <V> the type of the value
     * @return the value associated with the specified key, or null if the key is not found
     */
    public <V> V get(String key) {
        return (V) map.get(key);
    }
    
    /**
     * Retrieves the value associated with the specified key and casts it to the specified class type.
     *
     * @param clazz the class type to cast the value to
     * @param key the key whose associated value is to be returned
     * @param <V> the type of the value
     * @return the value associated with the specified key, or null if the key is not found
     */
    public <V> V get(Class<V> clazz, String key) {
        return get(key);
    }
    
    /**
     * Retrieves the byte value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the byte value associated with the specified key
     */
    public byte getByte(String key) {
        return get(Number.class, key).byteValue();
    }
    
    /**
     * Retrieves the short value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the short value associated with the specified key
     */
    public short getShort(String key) {
        return get(Number.class, key).shortValue();
    }
    
    /**
     * Retrieves the int value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the int value associated with the specified key
     */
    public int getInt(String key) {
        return get(Number.class, key).intValue();
    }
    
    /**
     * Retrieves the float value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the float value associated with the specified key
     */
    public float getFloat(String key) {
        return get(Number.class, key).floatValue();
    }
    
    /**
     * Retrieves the long value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the long value associated with the specified key
     */
    public long getLong(String key) {
        return get(Number.class, key).longValue();
    }
    
    /**
     * Retrieves the double value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the double value associated with the specified key
     */
    public double getDouble(String key) {
        return get(Number.class, key).doubleValue();
    }
    
    /**
     * Retrieves the boolean value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the boolean value associated with the specified key
     */
    public boolean getBoolean(String key) {
        return get(key);
    }
    
    /**
     * Retrieves the string value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the string value associated with the specified key
     */
    public String getString(String key) {
        return get(key);
    }
    
    /**
     * Retrieves the JSON object associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the JSON object associated with the specified key
     */
    public JSONObject getObject(String key) {
        return get(key);
    }
    
    /**
     * Retrieves the JSON array associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the JSON array associated with the specified key
     */
    public JSONArray getArray(String key) {
        return get(key);
    }
    
    /**
     * Retrieves the value associated with the specified key and deserializes it using the specified deserializer.
     *
     * @param deserializer the deserializer to use
     * @param key the key whose associated value is to be returned
     * @param <V> the type of the value
     * @return the deserialized value associated with the specified key
     */
    public <V> V get(JSONDeserializer<V> deserializer, String key) {
        return deserializer.deserializeJSON(new StringJSONReader(getString(key)));
    }
    
    /**
     * Puts the specified key-value pair into the JSON object.
     *
     * @param key the key to be associated with the specified value
     * @param value the value to be associated with the specified key
     * @return the current JSON object
     */
    public JSONObject put(String key, Object value) {
        map.put(key, value);
        return this;
    }
    
    /**
     * Puts the specified key-value pair into the JSON object after serializing the value using the specified serializer.
     *
     * @param key the key to be associated with the specified value
     * @param serializer the serializer to use
     * @param value the value to be serialized and associated with the specified key
     * @param <V> the type of the value
     * @return the current JSON object
     */
    public <V> JSONObject put(String key, JSONSerializer<V> serializer, V value) {
        AbstractJSONWriter out = new InlineJSONWriter();
        serializer.serializeJSON(value, out);
        put(key, out.getResult());
        return this;
    }
    
    /**
     * Removes the key-value pair associated with the specified key from the JSON object.
     *
     * @param key the key whose key-value pair is to be removed
     * @return the current JSON object
     */
    public JSONObject remove(String key) {
        map.remove(key);
        return this;
    }
    
    /**
     * Checks if the JSON object contains the specified key.
     *
     * @param key the key to check for
     * @return true if the JSON object contains the specified key, false otherwise
     */
    public boolean contains(String key) {
        return map.containsKey(key);
    }
    
    /**
     * Checks if the JSON object is empty.
     *
     * @return true if the JSON object is empty, false otherwise
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }
    
    /**
     * Returns the number of key-value pairs in the JSON object.
     *
     * @return the number of key-value pairs in the JSON object
     */
    public int size() {
        return map.size();
    }
    
    /**
     * Clears all key-value pairs from the JSON object.
     */
    public void clear() {
        map.clear();
    }
    
    /**
     * Returns a map representation of the JSON object.
     *
     * @return a map containing all key-value pairs in the JSON object
     */
    public Map<String, Object> toMap() {
        return map;
    }
}
