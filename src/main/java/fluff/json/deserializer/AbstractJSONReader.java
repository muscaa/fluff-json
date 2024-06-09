package fluff.json.deserializer;

public abstract class AbstractJSONReader {
	
	protected int current = 0;
	
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
		return get(++current);
	}
	
	public char back() {
		return get(--current);
	}
	
	public char peek() {
		return get(current);
	}
	
	public void set(int index) {
		current = index;
	}
}
