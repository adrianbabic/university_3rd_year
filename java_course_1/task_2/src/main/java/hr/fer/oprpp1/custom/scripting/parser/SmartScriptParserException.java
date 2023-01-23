package hr.fer.oprpp1.custom.scripting.parser;

/** This class is created for a custom Exception which
 * 	is used within another class.
 * 
 *  @author adrian
 *
 */
@SuppressWarnings("serial")
public class SmartScriptParserException extends RuntimeException {
	public SmartScriptParserException(String errorMessage) {
		super(errorMessage);
	}
}
