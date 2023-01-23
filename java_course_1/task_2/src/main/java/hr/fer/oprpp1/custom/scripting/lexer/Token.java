package hr.fer.oprpp1.custom.scripting.lexer;

import hr.fer.oprpp1.custom.scripting.elems.Element;

/**	Class is used for separating different tokens which are distinctive
 * 	by it's type and element.
 *  
 * 	@author adrian
 *
 */

public class Token {
	/** Type of token. **/
	private TokenType tokenType;
	/** element of token. **/
	private Element element; 
	
	
	/** Constructor for this class.
	 * 	
	 * @param TokenType which determines which type of token is used
	 * @param Object which stores element of the token
	 * @throws IllegalArgumentException if TokenType is null
	 * 
	 */
	public Token(TokenType type, Element element) {
		if(type == null) throw new IllegalArgumentException("Token type can not be null."); 
		this.tokenType = type;
		this.element = element;
	}
	
	/** Getter for element.
	 * 	
	 * @return Object which represents element of this token
	 * 
	 */
	public Element getElement() {
		return this.element;
	}
	
	/** Getter for type.
	 * 	
	 * @return TokenType which represents type of this token
	 * 
	 */
	public TokenType getTokenType() {
		return this.tokenType;
	}
}