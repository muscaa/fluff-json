package fluff.json.converter;

import fluff.json.deserializer.JSONDeserializer;
import fluff.json.serializer.JSONSerializer;

public interface JSONConverter<V> extends JSONSerializer<V>, JSONDeserializer<V>  {
	
}
