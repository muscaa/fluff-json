package fluff.json;

import java.util.HashMap;
import java.util.Map;

import fluff.json.deserializer.JSONDeserializer;
import fluff.json.deserializer.readers.StringJSONReader;
import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;
import fluff.json.serializer.writers.InlineJSONWriter;

public class JSONObject extends JSON {
	
	private final Map<String, Object> map = new HashMap<>();
	
	public <V> V get(String key) {
		return (V) map.get(key);
	}
	
	public <V> V get(Class<V> clazz, String key) {
		return get(key);
	}
	
	public byte getByte(String key) {
		return get(Number.class, key).byteValue();
	}
	
	public short getShort(String key) {
		return get(Number.class, key).shortValue();
	}
	
	public int getInt(String key) {
		return get(Number.class, key).intValue();
	}
	
	public float getFloat(String key) {
		return get(Number.class, key).floatValue();
	}
	
	public long getLong(String key) {
		return get(Number.class, key).longValue();
	}
	
	public double getDouble(String key) {
		return get(Number.class, key).doubleValue();
	}
	
	public <V> V get(JSONDeserializer<V> deserializer, String key) {
		return deserializer.deserializeJSON(new StringJSONReader(get(String.class, key)));
	}
	
	public JSONObject put(String key, Object value) {
		map.put(key, value);
		return this;
	}
	
	public <V> JSONObject put(String key, JSONSerializer<V> serializer, V value) {
		AbstractJSONWriter out = new InlineJSONWriter();
		serializer.serializeJSON(value, out);
		put(key, out.getResult());
		return this;
	}
	
	public JSONObject remove(String key) {
		map.remove(key);
		return this;
	}
	
	public boolean contains(String key) {
		return map.containsKey(key);
	}
	
	public boolean isEmpty() {
		return map.isEmpty();
	}
	
	public int size() {
		return map.size();
	}
	
	public void clear() {
		map.clear();
	}
	
	public Map<String, Object> toMap() {
		return map;
	}
}
