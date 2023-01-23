package hr.fer.oprpp1.custom.scripting.lexer;

/**
 * Enumeration with types of states that lexer can be in.
 *
 * @author adrian
 */
public enum LexerState {
	/** Represents lexer state which accepts all words, symbols and numbers. **/
	TEXT, 
	/** Represents lexer state which accepts only words. **/
	TAG, 
}
