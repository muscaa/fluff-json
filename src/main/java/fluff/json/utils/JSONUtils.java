package fluff.json.utils;

import fluff.functions.gen.Func;
import fluff.json.JSONArray;
import fluff.json.JSONException;
import fluff.json.JSONObject;
import fluff.json.converter.JSONConverters;
import fluff.json.deserializer.AbstractJSONReader;
import fluff.json.deserializer.JSONParser;
import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;

/**
 * Utility class for JSON serialization and deserialization.
 */
public class JSONUtils {
    
    /**
     * Serializes an object to JSON format using the provided {@link AbstractJSONWriter}.
     *
     * @param value the object to serialize
     * @param out the JSON writer to write the serialized value to
     * @return the JSON writer after writing the serialized value
     */
    public static AbstractJSONWriter serialize(Object value, AbstractJSONWriter out) {
        if (value == null) {
            out.write(null);
            return out;
        }
        
        if (value instanceof JSONSerializer s) {
            s.serializeJSON(value, out);
            return out;
        }
        
        JSONSerializer s = JSONConverters.getSerializer(value);
        if (s != null) {
            s.serializeJSON(value, out);
            return out;
        }
        
        out.writeQuoted(String.valueOf(value));
        
        return out;
    }
    
    /**
     * Deserializes JSON content from the provided {@link AbstractJSONReader}.
     *
     * @param <V> the type of the deserialized value
     * @param objectFunc the function to create a new JSON object
     * @param arrayFunc the function to create a new JSON array
     * @param in the JSON reader to read the serialized value from
     * @return the deserialized value, or null if deserialization fails
     */
    public static <V> V deserialize(Func<JSONObject> objectFunc, Func<JSONArray> arrayFunc, AbstractJSONReader in) {
        try {
            JSONParser parser = new JSONParser(objectFunc, arrayFunc, in);
            return (V) parser.parse();
        } catch (JSONException e) {}
        return null;
    }
    
    /**
     * Checks if a character is a digit.
     *
     * @param c the character to check
     * @return true if the character is a digit, false otherwise
     */
    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
    
    /**
     * Checks if a character is an alphabet letter.
     *
     * @param c the character to check
     * @return true if the character is an alphabet letter, false otherwise
     */
    public static boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    
    /**
     * Quotes and escapes a string value for JSON format.
     *
     * @param value the value to quote and escape
     * @return the quoted and escaped string
     */
    public static String quote(Object value) {
        return "\"" + escape(String.valueOf(value)) + "\"";
    }
    
    /**
     * Escapes a string for JSON format.
     *
     * @param s the string to escape
     * @return the escaped string
     */
    public static String escape(String s) {
        if (s == null) return null;
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            char escape = switch (c) {
                case '"' -> '"';
                case '\\' -> '\\';
                case '/' -> '/';
                case '\b' -> 'b';
                case '\f' -> 'f';
                case '\n' -> 'n';
                case '\r' -> 'r';
                case '\t' -> 't';
                default -> 0;
            };
            
            if (escape != 0) {
                sb.append('\\').append(escape);
                continue;
            }
            
            if ((c >= '\u0000' && c <= '\u001F') || (c >= '\u007F' && c <= '\u009F') || (c >= '\u2000' && c <= '\u20FF')) {
                String hex = Integer.toHexString(c);
                sb.append("\\u");
                for (int j = 0; j < 4 - hex.length(); j++) {
                    sb.append('0');
                }
                sb.append(hex.toUpperCase());
                continue;
            }
            
            sb.append(c);
        }
        
        return sb.toString();
    }
}
