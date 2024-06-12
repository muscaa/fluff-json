package fluff.json.serializer.serializers;

import fluff.json.JSONObject;
import fluff.json.converter.JSONConverters;
import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;

public class JSONObjectJSONSerializer implements JSONSerializer<JSONObject> {
	
	@Override
	public void serializeJSON(JSONObject value, AbstractJSONWriter out) {
		JSONConverters.MAP.serializeJSON(value.toMap(), out);
	}
}
