package hr.fer.oprpp1.custom.scripting.lexer;


/**
 * Enumeration of types of token that are possible in our task.
 *
 * @author adrian
 */

public enum TokenType {
	/** Represents a special group of characters that are accepted by different rules to
	 * 	the usual text. **/
	/** Represents a String of one or more characters. **/
	TEXT,
	/** Suggets there are no more tokens left, end of file. **/
	FUNCTION,
	STRING,
	OPERATOR,
	TAG_START,
	TAG_END,
	NUMBER,
	VARIABLE,
	EOF, 
}
