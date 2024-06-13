package fluff.json.converter.converters;

import fluff.json.converter.JSONConverter;
import fluff.json.deserializer.AbstractJSONReader;
import fluff.json.serializer.AbstractJSONWriter;

/**
 * A JSON converter for {@link Number} values.
 * Implements the {@link JSONConverter} interface to provide serialization and deserialization of number values.
 */
public class NumberJSONConverter implements JSONConverter<Number> {
    
    @Override
    public void serializeJSON(Number value, AbstractJSONWriter out) {
        out.write(value);
    }
    
    @Override
    public Number deserializeJSON(AbstractJSONReader in) {
        String text = in.text();
        if (text.contains(".")) {
            return Double.parseDouble(text);
        }
        return Long.parseLong(text);
    }
}
