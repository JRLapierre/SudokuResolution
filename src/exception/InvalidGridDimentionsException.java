package exception;

public class InvalidGridDimentionsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidGridDimentionsException() {
		super("The grid must be 9 by 9");
	}

}
