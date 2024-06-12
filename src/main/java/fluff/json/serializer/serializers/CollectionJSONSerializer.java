package fluff.json.serializer.serializers;

import java.util.Collection;

import fluff.json.deserializer.lexer.JSONTokenType;
import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;
import fluff.json.utils.JSONUtils;

public class CollectionJSONSerializer implements JSONSerializer<Collection<?>> {
	
	@Override
	public void serializeJSON(Collection<?> value, AbstractJSONWriter out) {
		out.token(JSONTokenType.OPEN_SQUARE);
		
		boolean first = true;
		for (Object o : value) {
			if (!first) out.token(JSONTokenType.COMMA);
			else first = false;
			
			JSONUtils.serialize(o, out);
		}
		
		out.token(JSONTokenType.CLOSE_SQUARE);
	}
}
