package fluff.json.serializer.serializers;

import java.util.Map;

import fluff.json.deserializer.lexer.JSONTokenType;
import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;
import fluff.json.utils.JSONUtils;

public class MapJSONSerializer implements JSONSerializer<Map<String, ?>> {
	
	@Override
	public void serializeJSON(Map<String, ?> value, AbstractJSONWriter out) {
		out.token(JSONTokenType.OPEN_CURLY);
		
		boolean first = true;
		for (Map.Entry<String, ?> e : value.entrySet()) {
			if (!first) out.token(JSONTokenType.COMMA);
			else first = false;
			
			out.writeQuoted(e.getKey());
			out.token(JSONTokenType.COLON);
			
			JSONUtils.serialize(e.getValue(), out);
		}
		
		out.token(JSONTokenType.CLOSE_CURLY);
	}
}
