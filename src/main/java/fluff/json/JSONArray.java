package fluff.json;

import java.util.ArrayList;
import java.util.List;

public class JSONArray extends JSON {
	
	private final List<Object> list = new ArrayList<>();
	
	public <V> V get(Class<V> clazz, int index) {
		return (V) list.get(index);
	}
	
	public boolean getBoolean(int index) {
		return get(boolean.class, index);
	}
	
	public byte getByte(int index) {
		return get(Number.class, index).byteValue();
	}
	
	public char getChar(int index) {
		return get(char.class, index);
	}
	
	public short getShort(int index) {
		return get(Number.class, index).shortValue();
	}
	
	public int getInt(int index) {
		return get(Number.class, index).intValue();
	}
	
	public float getFloat(int index) {
		return get(Number.class, index).floatValue();
	}
	
	public long getLong(int index) {
		return get(Number.class, index).longValue();
	}
	
	public double getDouble(int index) {
		return get(Number.class, index).doubleValue();
	}
	
	public String getString(int index) {
		return get(String.class, index);
	}
	
	public JSONObject getObject(int index) {
		return get(JSONObject.class, index);
	}
	
	public JSONArray getArray(int index) {
		return get(JSONArray.class, index);
	}
	
	// more getters
	
	public JSONArray set(int index, Object value) {
		list.set(index, value);
		return this;
	}
	
	public JSONArray add(Object value) {
		list.add(value);
		return this;
	}
	
	public JSONArray add(int index, Object value) {
		list.add(index, value);
		return this;
	}
	
	public JSONArray remove(Object value) {
		list.remove(value);
		return this;
	}
	
	public JSONArray remove(int index) {
		list.remove(index);
		return this;
	}
	
	public boolean contains(Object value) {
		return list.contains(value);
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public int size() {
		return list.size();
	}
	
	public void clear() {
		list.clear();
	}
	
	public List<Object> toList() {
		return list;
	}
}
