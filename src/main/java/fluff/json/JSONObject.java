package fluff.json;

import java.util.HashMap;
import java.util.Map;

public class JSONObject extends JSON {
	
	private final Map<String, Object> map = new HashMap<>();
	
	public <V> V get(Class<V> clazz, String key) {
		return (V) map.get(key);
	}
	
	public boolean getBoolean(String key) {
		return get(boolean.class, key);
	}
	
	public byte getByte(String key) {
		return get(Number.class, key).byteValue();
	}
	
	public char getChar(String key) {
		return get(char.class, key);
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
	
	public String getString(String key) {
		return get(String.class, key);
	}
	
	public JSONObject getObject(String key) {
		return get(JSONObject.class, key);
	}
	
	public JSONArray getArray(String key) {
		return get(JSONArray.class, key);
	}
	
	// more getters for custom classes or deserializers or something
	
	public JSONObject put(String key, Object value) {
		map.put(key, value);
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
