package fluff.json.serializer.serializers;

import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;

public class CharJSONSerializer implements JSONSerializer<Character> {
	
	@Override
	public void serializeJSON(Character value, AbstractJSONWriter out) {
		out.write(value);
	}
}
