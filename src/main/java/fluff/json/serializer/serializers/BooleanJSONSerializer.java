package fluff.json.serializer.serializers;

import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;

public class BooleanJSONSerializer implements JSONSerializer<Boolean> {
	
	@Override
	public void serializeJSON(Boolean value, AbstractJSONWriter out) {
		out.write(value);
	}
}
