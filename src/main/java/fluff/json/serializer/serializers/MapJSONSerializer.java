package fluff.json.serializer.serializers;

import java.util.Map;

import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;

public class MapJSONSerializer implements JSONSerializer<Map<String, ?>> {
	
	@Override
	public void serializeJSON(Map<String, ?> value, AbstractJSONWriter out) {
		out.begin("{");
		
		boolean first = true;
		for (Map.Entry<String, ?> e : value.entrySet()) {
			if (!first) out.mark(",");
			else first = false;
			
			out.writeQuoted(e.getKey());
			out.write(": ");
			
			JSONSerializer.serialize(e.getValue(), out);
		}
		
		out.end("}");
	}
}
