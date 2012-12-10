package stack;

public class UnderFlowException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UnderFlowException() {}
	
	public UnderFlowException(String message) {
		super(message);
	}
	
	public UnderFlowException(String message, Throwable cause) {
		super(message, cause);
	}
}
