package fluff.json.serializer.serializers;

import fluff.json.JSONArray;
import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;

public class JSONArrayJSONSerializer implements JSONSerializer<JSONArray> {
	
	@Override
	public void serializeJSON(JSONArray value, AbstractJSONWriter out) {
		JSONSerializer.serialize(value.toList(), out);
	}
}
