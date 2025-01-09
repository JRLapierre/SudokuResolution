package exception;

public class RepeatingNumberException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepeatingNumberException(short value) {
		super("the value '" + value + "' is repeated multiple times");
	}

}
