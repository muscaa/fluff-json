package fluff.json.converter.converters;

import fluff.json.converter.JSONConverter;
import fluff.json.deserializer.AbstractJSONReader;
import fluff.json.serializer.AbstractJSONWriter;

public class StringJSONConverter implements JSONConverter<String> {
	
	@Override
	public void serializeJSON(String value, AbstractJSONWriter out) {
		out.writeQuoted(value);
	}
	
	@Override
	public String deserializeJSON(AbstractJSONReader in) {
		return in.text();
	}
}
