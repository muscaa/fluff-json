package fluff.json;

import fluff.json.deserializer.readers.StringJSONReader;
import fluff.json.serializer.writers.InlineJSONWriter;
import fluff.json.serializer.writers.PrettyJSONWriter;
import fluff.json.utils.JSONUtils;

public abstract class JSON {
	
	@Override
	public String toString() {
		return JSONUtils.serialize(this, new InlineJSONWriter()).getResult();
	}
	
	public String toPrettyString() {
		return JSONUtils.serialize(this, new PrettyJSONWriter()).getResult();
	}
	
	public static JSONObject object() {
		return new JSONObject();
	}
	
	public static JSONObject object(String json) {
		return JSONUtils.deserialize(new StringJSONReader(json));
	}
	
	public static JSONArray array() {
		return new JSONArray();
	}
	
	public static JSONArray array(String json) {
		return JSONUtils.deserialize(new StringJSONReader(json));
	}
}
