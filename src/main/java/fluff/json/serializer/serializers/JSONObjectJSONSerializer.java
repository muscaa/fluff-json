package fluff.json.serializer.serializers;

import fluff.json.JSONObject;
import fluff.json.converter.JSONConverters;
import fluff.json.serializer.AbstractJSONWriter;
import fluff.json.serializer.JSONSerializer;

/**
 * A JSON serializer for {@link JSONObject} objects.
 * This serializer converts a {@link JSONObject} into a JSON object representation.
 */
public class JSONObjectJSONSerializer implements JSONSerializer<JSONObject> {
    
    @Override
    public void serializeJSON(JSONObject value, AbstractJSONWriter out) {
        JSONConverters.MAP.serializeJSON(value.getMap(), out);
    }
}
