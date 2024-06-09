package fluff.json.serializer.serializers;

import java.util.Collection;

import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;

public class CollectionJSONSerializer implements JSONSerializer<Collection<?>> {
	
	@Override
	public void serializeJSON(Collection<?> value, AbstractJSONWriter out) {
		out.begin("[");
		
		boolean first = true;
		for (Object o : value) {
			if (!first) out.mark(",");
			else first = false;
			
			JSONSerializer.serialize(o, out);
		}
		
		out.end("]");
	}
}
