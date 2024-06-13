package fluff.json.converter.converters;

import fluff.json.converter.JSONConverter;
import fluff.json.deserializer.AbstractJSONReader;
import fluff.json.serializer.AbstractJSONWriter;

/**
 * A JSON converter for {@link String} values.
 * Implements the {@link JSONConverter} interface to provide serialization and deserialization of string values.
 */
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
