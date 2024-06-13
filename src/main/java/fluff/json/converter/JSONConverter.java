package fluff.json.converter;

import fluff.json.deserializer.JSONDeserializer;
import fluff.json.serializer.JSONSerializer;

/**
 * A JSONConverter is responsible for both serializing and deserializing objects of type {@code V} to and from JSON format.
 * It combines the functionalities of {@link JSONSerializer} and {@link JSONDeserializer}.
 *
 * @param <V> the type of object this converter handles
 */
public interface JSONConverter<V> extends JSONSerializer<V>, JSONDeserializer<V> {
	
}
