package fluff.json.serializer;

import fluff.json.JSONConfig;

public interface JSONSerializer<V> {
	
	void serializeJSON(V value, AbstractJSONWriter out);
	
	static AbstractJSONWriter serialize(Object value, AbstractJSONWriter out) {
		if (value == null) {
			out.write(null);
			return out;
		}
		
		if (value instanceof JSONSerializer s) {
			s.serializeJSON(value, out);
			return out;
		}
		
		JSONSerializer s = JSONConfig.serializer(value);
		if (s != null) {
			s.serializeJSON(value, out);
			return out;
		}
		
		out.writeQuoted(String.valueOf(value));
		
		return out;
	}
}
