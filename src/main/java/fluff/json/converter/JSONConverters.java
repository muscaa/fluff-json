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

/**
 * A utility class that holds and manages JSON converters, serializers, and deserializers.
 * Provides methods to register and retrieve JSON serializers and deserializers for various types.
 */
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
    
    /**
     * Registers a JSON converter for the specified classes.
     *
     * @param <V> the type of the value
     * @param converter the JSON converter to register
     * @param classes the classes to associate with the converter
     * @return the registered JSON converter
     */
    public static <V> JSONConverter<V> register(JSONConverter<V> converter, Class<?>... classes) {
        register((JSONSerializer<V>) converter, classes);
        register((JSONDeserializer<V>) converter, classes);
        return converter;
    }
    
    /**
     * Registers a JSON serializer for the specified classes.
     *
     * @param <V> the type of the value
     * @param serializer the JSON serializer to register
     * @param classes the classes to associate with the serializer
     * @return the registered JSON serializer
     */
    public static <V> JSONSerializer<V> register(JSONSerializer<V> serializer, Class<?>... classes) {
        for (Class<?> c : classes) {
            SERIALIZERS.put(c, serializer);
        }
        return serializer;
    }
    
    /**
     * Registers a JSON deserializer for the specified classes.
     *
     * @param <V> the type of the value
     * @param deserializer the JSON deserializer to register
     * @param classes the classes to associate with the deserializer
     * @return the registered JSON deserializer
     */
    public static <V> JSONDeserializer<V> register(JSONDeserializer<V> deserializer, Class<?>... classes) {
        for (Class<?> c : classes) {
            DESERIALIZERS.put(c, deserializer);
        }
        return deserializer;
    }
    
    /**
     * Retrieves the JSON serializer for the given value.
     *
     * @param <V> the type of the value
     * @param value the value to find a serializer for
     * @return the JSON serializer, or null if not found
     */
    public static <V> JSONSerializer<V> getSerializer(Object value) {
        for (Map.Entry<Class<?>, JSONSerializer<?>> e : SERIALIZERS.entrySet()) {
            if (e.getKey().isInstance(value)) {
                return (JSONSerializer<V>) e.getValue();
            }
        }
        return null;
    }
    
    /**
     * Retrieves the JSON serializer for the given class.
     *
     * @param <V> the type of the value
     * @param clazz the class to find a serializer for
     * @return the JSON serializer, or null if not found
     */
    public static <V> JSONSerializer<V> getSerializer(Class<?> clazz) {
        for (Map.Entry<Class<?>, JSONSerializer<?>> e : SERIALIZERS.entrySet()) {
            if (e.getKey().isAssignableFrom(clazz)) {
                return (JSONSerializer<V>) e.getValue();
            }
        }
        return null;
    }
    
    /**
     * Retrieves the JSON deserializer for the given value.
     *
     * @param <V> the type of the value
     * @param value the value to find a deserializer for
     * @return the JSON deserializer, or null if not found
     */
    public static <V> JSONDeserializer<V> getDeserializer(Object value) {
        for (Map.Entry<Class<?>, JSONDeserializer<?>> e : DESERIALIZERS.entrySet()) {
            if (e.getKey().isInstance(value)) {
                return (JSONDeserializer<V>) e.getValue();
            }
        }
        return null;
    }
    
    /**
     * Retrieves the JSON deserializer for the given class.
     *
     * @param <V> the type of the value
     * @param clazz the class to find a deserializer for
     * @return the JSON deserializer, or null if not found
     */
    public static <V> JSONDeserializer<V> getDeserializer(Class<?> clazz) {
        for (Map.Entry<Class<?>, JSONDeserializer<?>> e : DESERIALIZERS.entrySet()) {
            if (e.getKey().isAssignableFrom(clazz)) {
                return (JSONDeserializer<V>) e.getValue();
            }
        }
        return null;
    }
}
