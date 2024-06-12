package fluff.json.serializer.serializers;

import fluff.json.JSONArray;
import fluff.json.converter.JSONConverters;
import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;

public class JSONArrayJSONSerializer implements JSONSerializer<JSONArray> {
	
	@Override
	public void serializeJSON(JSONArray value, AbstractJSONWriter out) {
		JSONConverters.COLLECTION.serializeJSON(value.toList(), out);
	}
}
