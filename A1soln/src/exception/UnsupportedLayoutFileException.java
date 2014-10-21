package exception;

public class UnsupportedLayoutFileException extends Exception {

	public UnsupportedLayoutFileException(String string) {
		System.err.println("UnsupportedLayoutFileException: " + string);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
