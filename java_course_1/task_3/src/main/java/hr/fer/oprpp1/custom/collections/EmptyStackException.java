package hr.fer.oprpp1.custom.collections;

/** This class is created for a custom Exception which
 * 	is used within another class.
 * 
 *  @author adrian
 *
 */
@SuppressWarnings("serial")
public class EmptyStackException extends RuntimeException {
	public EmptyStackException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
