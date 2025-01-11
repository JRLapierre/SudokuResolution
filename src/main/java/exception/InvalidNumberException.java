package exception;

public class InvalidNumberException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidNumberException(short number) {
		super("the number '" + number + "' has no place in a sudoku grid");
	}

}
