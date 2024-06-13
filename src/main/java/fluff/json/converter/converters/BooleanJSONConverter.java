package fluff.json.converter.converters;

import fluff.json.converter.JSONConverter;
import fluff.json.deserializer.AbstractJSONReader;
import fluff.json.serializer.AbstractJSONWriter;

/**
 * A JSON converter for {@link Boolean} values.
 * Implements the {@link JSONConverter} interface to provide serialization and deserialization of boolean values.
 */
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
