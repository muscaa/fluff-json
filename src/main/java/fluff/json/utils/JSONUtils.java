package fluff.json.utils;

import fluff.json.JSONException;
import fluff.json.converter.JSONConverters;
import fluff.json.deserializer.AbstractJSONReader;
import fluff.json.deserializer.JSONParser;
import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;

public class JSONUtils {
	
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
	
	public static <V> V deserialize(AbstractJSONReader in) {
		try {
			JSONParser parser = new JSONParser(in);
			return (V) parser.parse();
		} catch (JSONException e) {}
		return null;
	}
	
    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
    
    public static boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
	
	public static String quote(Object value) {
		return "\"" + escape(String.valueOf(value)) + "\"";
	}
	
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
