package hm.exceptions;

/**
 * Exception-Handling im ServiceManagement
 */
public class BuchungsException extends Exception {

	private static final long serialVersionUID = 1L;

	public BuchungsException(String message) {
		super(message);
	}
	
	public BuchungsException(String message, Throwable cause) {
		super(message, cause);
	}
}
