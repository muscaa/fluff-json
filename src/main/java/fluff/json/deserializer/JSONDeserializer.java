package fluff.json.deserializer;

public interface JSONDeserializer<V> {
	
	V deserializeJSON(AbstractJSONReader in);
}
