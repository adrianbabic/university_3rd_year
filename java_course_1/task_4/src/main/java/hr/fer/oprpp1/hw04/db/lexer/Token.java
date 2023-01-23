package hr.fer.oprpp1.hw04.db.lexer;

/**	Class is used for separating different tokens which are distinctive
 * 	by it's type and value.
 *  
 * 	@author adrian
 *
 */

public class Token {
	/** Type of token. **/
	private TokenType tokenType;
	/** value of token. **/
	private String value; 
	
	
	/** Constructor for this class.
	 * 	
	 * @param TokenType which determines which type of token is used
	 * @param Object which stores value of the token
	 * @throws IllegalArgumentException if TokenType is null
	 * 
	 */
	public Token(TokenType type, String value) {
		if(type == null) throw new IllegalArgumentException("Token type can not be null."); 
		this.tokenType = type;
		this.value = value;
	}
	
	/** Getter for value.
	 * 	
	 * @return Object which represents value of this token
	 * 
	 */
	public String getValue() {
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