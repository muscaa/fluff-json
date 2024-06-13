package fluff.json.converter.converters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import fluff.json.converter.JSONConverter;
import fluff.json.deserializer.AbstractJSONReader;
import fluff.json.serializer.AbstractJSONWriter;

/**
 * A JSON converter for {@link LocalDateTime} values.
 * Implements the {@link JSONConverter} interface to provide serialization and deserialization of {@link LocalDateTime} values.
 */
public class LocalDateTimeJSONConverter implements JSONConverter<LocalDateTime> {
    
    /**
     * The {@link DateTimeFormatter} used for formatting and parsing {@link LocalDateTime} values.
     */
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @Override
    public void serializeJSON(LocalDateTime value, AbstractJSONWriter out) {
        out.writeQuoted(value.format(FORMATTER));
    }
    
    @Override
    public LocalDateTime deserializeJSON(AbstractJSONReader in) {
        return LocalDateTime.parse(in.text(), FORMATTER);
    }
}
