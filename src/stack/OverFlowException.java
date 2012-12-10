package stack;

public class OverFlowException extends Exception {

	private static final long serialVersionUID = 1L;

	public OverFlowException() {}
	
	public OverFlowException(String message) {
		super(message);
	}
	
	public OverFlowException(String message, Throwable cause) {
		super(message, cause);
	}
}
