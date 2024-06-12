package fluff.json.deserializer.readers;

import fluff.json.deserializer.AbstractJSONReader;

public class StringJSONReader extends AbstractJSONReader {
	
	private final String text;
	
	public StringJSONReader(String text) {
		this.text = text;
	}
	
	@Override
	public String text() {
		return text;
	}
	
	@Override
	public char get(int index) {
		return text.charAt(index);
	}
	
	@Override
	public int size() {
		return text.length();
	}
}
