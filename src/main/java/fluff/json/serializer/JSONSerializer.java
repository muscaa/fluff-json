package fluff.json.serializer;

public interface JSONSerializer<V> {
	
	void serializeJSON(V value, AbstractJSONWriter out);
}
