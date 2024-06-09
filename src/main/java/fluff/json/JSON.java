package fluff.json;

import fluff.json.deserializer.JSONDeserializer;
import fluff.json.serializer.JSONSerializer;
import fluff.json.serializer.writers.InlineJSONWriter;
import fluff.json.serializer.writers.PrettyJSONWriter;

public abstract class JSON {
	
	@Override
	public String toString() {
		return JSONSerializer.serialize(this, new InlineJSONWriter()).getResult();
	}
	
	public String toPrettyString() {
		return JSONSerializer.serialize(this, new PrettyJSONWriter()).getResult();
	}
	
	public static JSONObject object() {
		return new JSONObject();
	}
	
	public static JSONObject object(String json) {
		return JSONDeserializer.deserialize(json);
	}
	
	public static JSONArray array() {
		return new JSONArray();
	}
	
	public static JSONArray array(String json) {
		return JSONDeserializer.deserialize(json);
	}
}
