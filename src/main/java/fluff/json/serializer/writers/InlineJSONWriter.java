package fluff.json.serializer.writers;

import fluff.json.serializer.AbstractJSONWriter;

public class InlineJSONWriter extends AbstractJSONWriter {
	
	@Override
	public void begin(String prefix) {
		write(prefix);
	}
	
	@Override
	public void mark(String infix) {
		write(infix);
	}
	
	@Override
	public void end(String suffix) {
		write(suffix);
	}
}
