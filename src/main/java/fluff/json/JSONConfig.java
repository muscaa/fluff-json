package fluff.json;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import fluff.json.serializer.JSONSerializer;
import fluff.json.serializer.serializers.BooleanJSONSerializer;
import fluff.json.serializer.serializers.CharJSONSerializer;
import fluff.json.serializer.serializers.CollectionJSONSerializer;
import fluff.json.serializer.serializers.JSONArrayJSONSerializer;
import fluff.json.serializer.serializers.JSONObjectJSONSerializer;
import fluff.json.serializer.serializers.MapJSONSerializer;
import fluff.json.serializer.serializers.NumberJSONSerializer;
import fluff.json.serializer.serializers.StringJSONSerializer;

public class JSONConfig {
	
	public static String TAB = "\t";
	
	private static final Map<Class<?>, JSONSerializer<?>> SERIALIZERS = new HashMap<>();
	
	public static final JSONSerializer<Boolean> BOOLEAN = registerSerializer(new BooleanJSONSerializer(), boolean.class, Boolean.class);
	public static final JSONSerializer<Character> CHAR = registerSerializer(new CharJSONSerializer(), char.class, Character.class);
	public static final JSONSerializer<Number> NUMBER = registerSerializer(new NumberJSONSerializer(),
			byte.class, short.class, int.class, float.class, long.class, double.class, Number.class);
	public static final JSONSerializer<String> STRING = registerSerializer(new StringJSONSerializer(), String.class);
	public static final JSONSerializer<Collection<?>> COLLECTION = registerSerializer(new CollectionJSONSerializer(), Collection.class);
	public static final JSONSerializer<Map<String, ?>> MAP = registerSerializer(new MapJSONSerializer(), Map.class);
	public static final JSONSerializer<JSONObject> JSON_OBJECT = registerSerializer(new JSONObjectJSONSerializer(), JSONObject.class);
	public static final JSONSerializer<JSONArray> JSON_ARRAY = registerSerializer(new JSONArrayJSONSerializer(), JSONArray.class);
	
	public static <V> JSONSerializer<V> registerSerializer(JSONSerializer<V> serializer, Class<?>... classes) {
		for (Class<?> c : classes) {
			SERIALIZERS.put(c, serializer);
		}
		return serializer;
	}
	
	public static <V> JSONSerializer<V> serializer(Object value) {
		for (Map.Entry<Class<?>, JSONSerializer<?>> e : SERIALIZERS.entrySet()) {
			if (e.getKey().isInstance(value)) {
				return (JSONSerializer<V>) e.getValue();
			}
		}
		return null;
	}
}
