package fluff.json.converter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import fluff.json.JSONArray;
import fluff.json.JSONObject;
import fluff.json.converter.converters.BooleanJSONConverter;
import fluff.json.converter.converters.LocalDateTimeJSONConverter;
import fluff.json.converter.converters.NumberJSONConverter;
import fluff.json.converter.converters.StringJSONConverter;
import fluff.json.deserializer.JSONDeserializer;
import fluff.json.serializer.JSONSerializer;
import fluff.json.serializer.serializers.CollectionJSONSerializer;
import fluff.json.serializer.serializers.JSONArrayJSONSerializer;
import fluff.json.serializer.serializers.JSONObjectJSONSerializer;
import fluff.json.serializer.serializers.MapJSONSerializer;

public class JSONConverters {
	
	private static final Map<Class<?>, JSONSerializer<?>> SERIALIZERS = new HashMap<>();
	private static final Map<Class<?>, JSONDeserializer<?>> DESERIALIZERS = new HashMap<>();
	
	// SERIALIZERS
	public static final JSONSerializer<JSONObject> JSON_OBJECT = register(new JSONObjectJSONSerializer(), JSONObject.class);
	public static final JSONSerializer<JSONArray> JSON_ARRAY = register(new JSONArrayJSONSerializer(), JSONArray.class);
	public static final JSONSerializer<Collection<?>> COLLECTION = register(new CollectionJSONSerializer(), Collection.class);
	public static final JSONSerializer<Map<String, ?>> MAP = register(new MapJSONSerializer(), Map.class);
	
	// DESERIALIZERS
	
	// CONVERTERS
	public static final JSONConverter<Boolean> BOOLEAN = register(new BooleanJSONConverter(), boolean.class, Boolean.class);
	public static final JSONConverter<Number> NUMBER = register(new NumberJSONConverter(),
			byte.class, short.class, int.class, float.class, long.class, double.class, Number.class);
	public static final JSONConverter<String> STRING = register(new StringJSONConverter(), String.class);
	public static final JSONConverter<LocalDateTime> LOCAL_DATE_TIME = register(new LocalDateTimeJSONConverter(), LocalDateTime.class);
	
	public static <V> JSONConverter<V> register(JSONConverter<V> converter, Class<?>... classes) {
		register((JSONSerializer<V>) converter, classes);
		register((JSONDeserializer<V>) converter, classes);
		return converter;
	}
	
	public static <V> JSONSerializer<V> register(JSONSerializer<V> serializer, Class<?>... classes) {
		for (Class<?> c : classes) {
			SERIALIZERS.put(c, serializer);
		}
		return serializer;
	}
	
	public static <V> JSONDeserializer<V> register(JSONDeserializer<V> deserializer, Class<?>... classes) {
		for (Class<?> c : classes) {
			DESERIALIZERS.put(c, deserializer);
		}
		return deserializer;
	}
	
	public static <V> JSONSerializer<V> getSerializer(Object value) {
		for (Map.Entry<Class<?>, JSONSerializer<?>> e : SERIALIZERS.entrySet()) {
			if (e.getKey().isInstance(value)) {
				return (JSONSerializer<V>) e.getValue();
			}
		}
		return null;
	}
	
	public static <V> JSONSerializer<V> getSerializer(Class<?> clazz) {
		for (Map.Entry<Class<?>, JSONSerializer<?>> e : SERIALIZERS.entrySet()) {
			if (e.getKey().isAssignableFrom(clazz)) {
				return (JSONSerializer<V>) e.getValue();
			}
		}
		return null;
	}
	
	public static <V> JSONDeserializer<V> getDeserializer(Object value) {
		for (Map.Entry<Class<?>, JSONDeserializer<?>> e : DESERIALIZERS.entrySet()) {
			if (e.getKey().isInstance(value)) {
				return (JSONDeserializer<V>) e.getValue();
			}
		}
		return null;
	}
	
	public static <V> JSONDeserializer<V> getDeserializer(Class<?> clazz) {
		for (Map.Entry<Class<?>, JSONDeserializer<?>> e : DESERIALIZERS.entrySet()) {
			if (e.getKey().isAssignableFrom(clazz)) {
				return (JSONDeserializer<V>) e.getValue();
			}
		}
		return null;
	}
}
