package fluff.json.converter.converters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import fluff.json.converter.JSONConverter;
import fluff.json.deserializer.AbstractJSONReader;
import fluff.json.serializer.AbstractJSONWriter;

public class LocalDateTimeJSONConverter implements JSONConverter<LocalDateTime> {
	
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
