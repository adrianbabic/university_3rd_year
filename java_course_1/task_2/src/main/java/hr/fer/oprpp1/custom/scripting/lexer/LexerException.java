package hr.fer.oprpp1.custom.scripting.lexer;

/** This class is created for a custom Exception which
 * 	is used within another class.
 * 
 *  @author adrian
 *
 */
@SuppressWarnings("serial")
public class LexerException extends RuntimeException {
	public LexerException(String errorMessage) {
		super(errorMessage);
	}
}
