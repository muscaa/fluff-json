package fluff.json.serializer.writers;

import fluff.json.JSONConfig;
import fluff.json.serializer.AbstractJSONWriter;

public class PrettyJSONWriter extends AbstractJSONWriter {
	
	protected int tabs = 0;
	protected boolean marked = false;
	
	@Override
	public void write(Object value) {
		if (marked) {
			marked = false;
			writeTabs();
		}
		super.write(value);
	}
	
	@Override
	public void begin(String prefix) {
		newLine();
		writeTabs();
		write(prefix);
		tabs++;
		newLine();
		marked = true;
	}
	
	@Override
	public void mark(String infix) {
		write(infix);
		newLine();
		marked = true;
	}
	
	@Override
	public void end(String suffix) {
		newLine();
		tabs--;
		writeTabs();
		write(suffix);
	}
	
	protected void writeTabs() {
		for (int i = 0; i < tabs; i++) {
			write(JSONConfig.TAB);
		}
	}
	
	protected void newLine() {
		write("\n");
	}
}
