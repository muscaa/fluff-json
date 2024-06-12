package fluff.json.deserializer;

public abstract class AbstractJSONReader {
	
	protected int current = 0;
	
	public abstract String text();
	
	public abstract char get(int index);
	
	public abstract int size();
	
	public int remaining() {
		return size() - current() - 1;
	}
	
	public int current() {
		return current;
	}
	
	public boolean hasNext(int size) {
		return remaining() >= size;
	}
	
	public boolean hasNext() {
		return hasNext(1);
	}
	
	public char next() {
		return get(set(current() + 1));
	}
	
	public char back() {
		return get(set(current() - 1));
	}
	
	public char peek() {
		return get(current());
	}
	
	public int set(int index) {
		current = index;
		return current;
	}
}
