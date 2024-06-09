package fluff.json;

public class JSONException extends Exception {
	
	private static final long serialVersionUID = -5924198373571432499L;
	
	public JSONException() {
        super();
    }
	
    public JSONException(String message) {
        super(message);
    }
    
    public JSONException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public JSONException(Throwable cause) {
        super(cause);
    }
}
