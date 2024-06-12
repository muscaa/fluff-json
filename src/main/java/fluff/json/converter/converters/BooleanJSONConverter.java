package fluff.json.converter.converters;

import fluff.json.converter.JSONConverter;
import fluff.json.deserializer.AbstractJSONReader;
import fluff.json.serializer.AbstractJSONWriter;

public class BooleanJSONConverter implements JSONConverter<Boolean> {
	
	@Override
	public void serializeJSON(Boolean value, AbstractJSONWriter out) {
		out.write(value);
	}
	
	@Override
	public Boolean deserializeJSON(AbstractJSONReader in) {
		return Boolean.parseBoolean(in.text());
	}
}
