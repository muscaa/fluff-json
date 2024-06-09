package fluff.json.serializer.serializers;

import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;

public class NumberJSONSerializer implements JSONSerializer<Number> {
	
	@Override
	public void serializeJSON(Number value, AbstractJSONWriter out) {
		out.write(value);
	}
}
