package hr.fer.oprpp1.hw02.prob1;


/**
 * Enumeration of types of token that are possible in our task.
 *
 * @author adrian
 */

public enum TokenType {
	/** Suggets there are no more tokens left, end of file. **/
	EOF, 
	/** Represents a String of one or more characters. **/
	WORD, 
	/** Represents a number that can be written as type Long. **/
	NUMBER, 
	/** Everything other than a letter, number and spacing type. **/
	SYMBOL, 
}
