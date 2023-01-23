package hr.fer.oprpp1.hw02.prob1;

/**
 * Enumeration with types of states that lexer can be in.
 *
 * @author adrian
 */
public enum LexerState {
	/** Represents lexer state which accepts all words, symbols and numbers. **/
	BASIC, 
	/** Represents lexer state which accepts only words. **/
	EXTENDED, 
}
