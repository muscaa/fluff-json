package fluff.json;

import java.util.Iterator;
import java.util.List;

import fluff.functions.gen.obj._int.Func2ObjInt;
import fluff.json.deserializer.JSONDeserializer;
import fluff.json.deserializer.readers.StringJSONReader;
import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;
import fluff.json.serializer.writers.InlineJSONWriter;

/**
 * Represents a JSON array, which is a collection of ordered values.
 * Extends the {@link JSON} class.
 */
public class JSONArray extends JSON {
    
    protected final List<Object> list;
    
    /**
     * Creates a new JSON array with a list.
     *
     * @param list the list containing all values in the JSON array
     */
    public JSONArray(List<Object> list) {
    	this.list = list;
	}
    
    /**
     * Retrieves the value at the specified index.
     *
     * @param index the index of the value to retrieve
     * @param <V> the type of the value
     * @return the value at the specified index
     */
    public <V> V get(int index) {
        return (V) list.get(index);
    }
    
    /**
     * Retrieves the value at the specified index and casts it to the specified class type.
     *
     * @param clazz the class type to cast the value to
     * @param index the index of the value to retrieve
     * @param <V> the type of the value
     * @return the value at the specified index
     */
    public <V> V get(Class<V> clazz, int index) {
        return get(index);
    }
    
    /**
     * Retrieves the byte value at the specified index.
     *
     * @param index the index of the value to retrieve
     * @return the byte value at the specified index
     */
    public byte getByte(int index) {
        return get(Number.class, index).byteValue();
    }
    
    /**
     * Retrieves the short value at the specified index.
     *
     * @param index the index of the value to retrieve
     * @return the short value at the specified index
     */
    public short getShort(int index) {
        return get(Number.class, index).shortValue();
    }
    
    /**
     * Retrieves the int value at the specified index.
     *
     * @param index the index of the value to retrieve
     * @return the int value at the specified index
     */
    public int getInt(int index) {
        return get(Number.class, index).intValue();
    }
    
    /**
     * Retrieves the float value at the specified index.
     *
     * @param index the index of the value to retrieve
     * @return the float value at the specified index
     */
    public float getFloat(int index) {
        return get(Number.class, index).floatValue();
    }
    
    /**
     * Retrieves the long value at the specified index.
     *
     * @param index the index of the value to retrieve
     * @return the long value at the specified index
     */
    public long getLong(int index) {
        return get(Number.class, index).longValue();
    }
    
    /**
     * Retrieves the double value at the specified index.
     *
     * @param index the index of the value to retrieve
     * @return the double value at the specified index
     */
    public double getDouble(int index) {
        return get(Number.class, index).doubleValue();
    }
    
    /**
     * Retrieves the boolean value at the specified index.
     *
     * @param index the index of the value to retrieve
     * @return the boolean value at the specified index
     */
    public boolean getBoolean(int index) {
        return get(index);
    }
    
    /**
     * Retrieves the string value at the specified index.
     *
     * @param index the index of the value to retrieve
     * @return the string value at the specified index
     */
    public String getString(int index) {
        return get(index);
    }
    
    /**
     * Retrieves the JSON object at the specified index.
     *
     * @param index the index of the value to retrieve
     * @return the JSON object at the specified index
     */
    public JSONObject getObject(int index) {
        return get(index);
    }
    
    /**
     * Retrieves the JSON array at the specified index.
     *
     * @param index the index of the value to retrieve
     * @return the JSON array at the specified index
     */
    public JSONArray getArray(int index) {
        return get(index);
    }
    
    /**
     * Retrieves the value at the specified index and deserializes it using the specified deserializer.
     *
     * @param deserializer the deserializer to use
     * @param index the index of the value to retrieve
     * @param <V> the type of the value
     * @return the deserialized value at the specified index
     */
    public <V> V get(JSONDeserializer<V> deserializer, int index) {
        return deserializer.deserializeJSON(new StringJSONReader(getString(index)));
    }
    
    /**
     * Sets the value at the specified index.
     *
     * @param index the index at which to set the value
     * @param value the value to set
     * @return the current JSON array
     */
    public JSONArray set(int index, Object value) {
        list.set(index, value);
        return this;
    }
    
    /**
     * Sets the value at the specified index after serializing it using the specified serializer.
     *
     * @param index the index at which to set the value
     * @param serializer the serializer to use
     * @param value the value to serialize and set
     * @param <V> the type of the value
     * @return the current JSON array
     */
    public <V> JSONArray set(int index, JSONSerializer<V> serializer, V value) {
        AbstractJSONWriter out = new InlineJSONWriter();
        serializer.serializeJSON(value, out);
        set(index, out.getResult());
        return this;
    }
    
    /**
     * Adds a value to the end of the JSON array.
     *
     * @param value the value to add
     * @return the current JSON array
     */
    public JSONArray add(Object value) {
        list.add(value);
        return this;
    }
    
    /**
     * Adds a value to the end of the JSON array after serializing it using the specified serializer.
     *
     * @param serializer the serializer to use
     * @param value the value to serialize and add
     * @param <V> the type of the value
     * @return the current JSON array
     */
    public <V> JSONArray add(JSONSerializer<V> serializer, V value) {
        AbstractJSONWriter out = new InlineJSONWriter();
        serializer.serializeJSON(value, out);
        add(out.getResult());
        return this;
    }
    
    /**
     * Adds a value at the specified index of the JSON array.
     *
     * @param index the index at which to add the value
     * @param value the value to add
     * @return the current JSON array
     */
    public JSONArray add(int index, Object value) {
        list.add(index, value);
        return this;
    }
    
    /**
     * Adds a value at the specified index of the JSON array after serializing it using the specified serializer.
     *
     * @param index the index at which to add the value
     * @param serializer the serializer to use
     * @param value the value to serialize and add
     * @param <V> the type of the value
     * @return the current JSON array
     */
    public <V> JSONArray add(int index, JSONSerializer<V> serializer, V value) {
        AbstractJSONWriter out = new InlineJSONWriter();
        serializer.serializeJSON(value, out);
        add(index, out.getResult());
        return this;
    }
    
    /**
     * Removes the specified value from the JSON array.
     *
     * @param value the value to remove
     * @return the current JSON array
     */
    public JSONArray remove(Object value) {
        list.remove(value);
        return this;
    }
    
    /**
     * Removes the value at the specified index from the JSON array.
     *
     * @param index the index of the value to remove
     * @return the current JSON array
     */
    public JSONArray remove(int index) {
        list.remove(index);
        return this;
    }
    
    /**
     * Checks if the JSON array contains the specified value.
     *
     * @param value the value to check for
     * @return true if the JSON array contains the specified value, false otherwise
     */
    public boolean contains(Object value) {
        return list.contains(value);
    }
    
    /**
     * Checks if the JSON array is empty.
     *
     * @return true if the JSON array is empty, false otherwise
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    /**
     * Returns the number of values in the JSON array.
     *
     * @return the number of values in the JSON array
     */
    public int size() {
        return list.size();
    }
    
    /**
     * Clears all values from the JSON array.
     */
    public void clear() {
        list.clear();
    }
    
    /**
     * Returns the list representation of the JSON array.
     *
     * @return the list containing all values in the JSON array
     */
    public List<Object> getList() {
        return list;
    }
    
    /**
     * Returns an iterator that iterates over the JSON array.
     * 
     * @param <V> the type of the values
     * @param nextFunc the function to get the next value
     * @return an iterator that iterates over the JSON array
     */
	public <V> Iterable<V> iterate(Func2ObjInt<V, JSONArray> nextFunc) {
		return () -> new JSONArrayIterator<>(nextFunc);
	}
	
	protected class JSONArrayIterator<V> implements Iterator<V> {
		
		private final Func2ObjInt<V, JSONArray> nextFunc;
		private int index = 0;
		
		public JSONArrayIterator(Func2ObjInt<V, JSONArray> nextFunc) {
			this.nextFunc = nextFunc;
		}
		
		@Override
		public boolean hasNext() {
			return index + 1 < size();
		}
		
		@Override
		public V next() {
			return nextFunc.invoke(JSONArray.this, index++);
		}
	}
}
