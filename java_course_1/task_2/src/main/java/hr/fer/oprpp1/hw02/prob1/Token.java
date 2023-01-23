package hr.fer.oprpp1.hw02.prob1;

/**	Class is used for separating different tokens which are distinctive
 * 	by it's type and value.
 *  
 * 	@author adrian
 *
 */

public class Token {
	/** Type of token. **/
	private TokenType tokenType;
	/** Value of token. **/
	private Object value; 
	
	
	/** Constructor for this class.
	 * 	
	 * @param TokenType which determines which type of token is used
	 * @param Object which stores value of the token
	 * @throws IllegalArgumentException if TokenType is null
	 * 
	 */
	public Token(TokenType type, Object value) {
		if(type == null) throw new IllegalArgumentException("Token type can not be null."); 
		this.tokenType = type;
		this.value = value;
	}
	
	/** Getter for value.
	 * 	
	 * @return Object which represents value of this token
	 * 
	 */
	public Object getValue() {
		return this.value;
	}
	
	/** Getter for type.
	 * 	
	 * @return TokenType which represents type of this token
	 * 
	 */
	public TokenType getType() {
		return this.tokenType;
	}
}