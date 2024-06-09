package fluff.json.serializer.serializers;

import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;

public class StringJSONSerializer implements JSONSerializer<String> {
	
	@Override
	public void serializeJSON(String value, AbstractJSONWriter out) {
		out.writeQuoted(value);
	}
}
