package fluff.json;

import java.util.ArrayList;
import java.util.List;

import fluff.json.deserializer.JSONDeserializer;
import fluff.json.deserializer.readers.StringJSONReader;
import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;
import fluff.json.serializer.writers.InlineJSONWriter;

public class JSONArray extends JSON {
	
	private final List<Object> list = new ArrayList<>();
	
	public <V> V get(int index) {
		return (V) list.get(index);
	}
	
	public <V> V get(Class<V> clazz, int index) {
		return get(index);
	}
	
	public byte getByte(int index) {
		return get(Number.class, index).byteValue();
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
	
	public <V> V get(JSONDeserializer<V> deserializer, int index) {
		return deserializer.deserializeJSON(new StringJSONReader(get(String.class, index)));
	}
	
	public JSONArray set(int index, Object value) {
		list.set(index, value);
		return this;
	}
	
	public <V> JSONArray set(int index, JSONSerializer<V> serializer, V value) {
		AbstractJSONWriter out = new InlineJSONWriter();
		serializer.serializeJSON(value, out);
		set(index, out.getResult());
		return this;
	}
	
	public JSONArray add(Object value) {
		list.add(value);
		return this;
	}
	
	public <V> JSONArray add(JSONSerializer<V> serializer, V value) {
		AbstractJSONWriter out = new InlineJSONWriter();
		serializer.serializeJSON(value, out);
		add(out.getResult());
		return this;
	}
	
	public JSONArray add(int index, Object value) {
		list.add(index, value);
		return this;
	}
	
	public <V> JSONArray add(int index, JSONSerializer<V> serializer, V value) {
		AbstractJSONWriter out = new InlineJSONWriter();
		serializer.serializeJSON(value, out);
		add(index, out.getResult());
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
