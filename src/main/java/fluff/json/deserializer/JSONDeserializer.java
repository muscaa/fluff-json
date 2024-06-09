package fluff.json.deserializer;

import fluff.json.JSONException;
import fluff.json.deserializer.readers.StringJSONReader;

public interface JSONDeserializer<V> {
	
	V deserializeJSON(String value);
	
	static <V> V deserialize(String value) {
		try {
			JSONParser parser = new JSONParser(new StringJSONReader(value));
			return (V) parser.parse();
		} catch (JSONException e) {}
		return null;
	}
}
