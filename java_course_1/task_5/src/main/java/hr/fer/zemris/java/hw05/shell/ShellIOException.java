package hr.fer.zemris.java.hw05.shell;

/** This class is created for a custom Exception which
 * 	is used within another class.
 * 
 *  @author adrian
 *
 */
@SuppressWarnings("serial")
public class ShellIOException extends RuntimeException {
	public ShellIOException(String errorMessage) {
		super(errorMessage);
	}
}
