package fluff.json.serializer;

import fluff.json.utils.JSONUtils;

public abstract class AbstractJSONWriter {
	
	protected final StringBuilder out = new StringBuilder();
	
	public abstract void begin(String prefix);
	
	public abstract void mark(String infix);
	
	public abstract void end(String suffix);
	
	public void write(Object value) {
		out.append(value);
	}
	
	public void writeQuoted(Object value) {
		write(JSONUtils.quote(value));
	}
	
	public String getResult() {
		return out.toString().trim();
	}
}
