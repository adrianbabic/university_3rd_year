package hr.fer.oprpp1.hw04.db.lexer;


/**
 * Enumeration of types of token that are possible in our task.
 *
 * @author adrian
 */

public enum TokenType {
	/** Represents an attribute by which database will be filtered by. **/
	ATTRIBUTE, 
	/** Represents an operator which will be used for comparing. **/
	OPERATOR,
	/** Represents a literal which should be compared to a given attribute of all instances in the database. **/
	LITERAL,
	/** Represents a logical operator between conditional expressions. **/
	LOGICAL_OPERATOR,
	/** Represents an end of query. **/
	EOQ,
}
