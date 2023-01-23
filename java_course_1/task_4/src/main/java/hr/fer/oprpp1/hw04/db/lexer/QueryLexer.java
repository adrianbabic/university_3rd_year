package hr.fer.oprpp1.hw04.db.lexer;

public class QueryLexer {
	
	/** Stores input in char array. **/
	private char[] data; 
	/** Stores current token. **/
	private Token token; 
	/** Stores current position of char in char array. **/
	private int currentIndex; 
	
	public QueryLexer(String query) {
		this.token = null;
		this.currentIndex = 0;
		this.data = query.trim().toCharArray();
	}
	
	/** Generates next token in line and returns it.
	 * 	
	 * 	@throws LexerException if no more tokens can be generated
	 *	@throws LexerException if the token could not be generated
	 *	@return object of type Token
	 */
	public Token nextToken() {
		if(token != null && token.getType() == TokenType.EOQ) {
			throw new LexerException("No tokens available.");
		}
		
		if(currentIndex >= data.length) {
			token = new Token(TokenType.EOQ, null);
			return getToken();
		}
		
		skipBlanks();
		
		StringBuilder sb = new StringBuilder();
		char currentChar = data[currentIndex];
		
		if(isOperator() >= 0) {
			sb.append(currentChar);
			if(isOperator() == 0) {
				sb.append(data[++currentIndex]);
			}
			token = new Token(TokenType.OPERATOR, sb.toString());
			currentIndex++;
			return getToken();
		}
		
		
		if(currentChar == '"') {
			currentChar = data[++currentIndex];
			while(currentChar != '"') {
				sb.append(currentChar);
				currentChar = data[++currentIndex];
			}
			currentIndex++;
			token = new Token(TokenType.LITERAL, sb.toString());
			return getToken();
		}
		
		while(Character.isLetterOrDigit(currentChar)) {
			sb.append(currentChar);
			if(currentIndex >= data.length - 1)
				break;
//				throw new LexerException("Invalid input! \nHINT: Quotation marks for string literal might be missing.");
			currentChar = data[++currentIndex];
		}
		
		String result = sb.toString();
		
		if(result.toLowerCase().equals("and")) {
			token = new Token(TokenType.LOGICAL_OPERATOR, null);
			return getToken();
		}
		
		if(result.equals("LIKE")) {
			token = new Token(TokenType.OPERATOR, result);
			return getToken();
		}
		
		if(result.equals("jmbag") || result.equals("lastName") || result.equals("firstName")) {
			token = new Token(TokenType.ATTRIBUTE, result);
			return getToken();
		}
		
		throw new LexerException("Lexer did not manage to produce a token! "
				+ "\nHINT: Check your attribute names, operators and logical operators!");
	}
	
	/** Getter for the current token. Can be called multiple times with same return.
	 * 	Does not generate new tokens, only returns the current one.
	 * 	
	 * 	@return object of type Token
	 */
	public Token getToken() {
		return token;
	}
	
	/** Private method removes all the empty characters such as space, tabulator
	 * 	and tranisitions to a new row.
	 *
	 */
	private void skipBlanks() {
		while(currentIndex < data.length) {
			char c = data[currentIndex];
			if(c == ' ' || c == '\t' || c == '\r' || c == '\n') {
				currentIndex++;
				continue;
			}
			break;
		}
	} 
	
	/** This private method helps determine if the current character is an operator.
	 * 	In case the current character is an operator, method checks if the next character is 
	 * 	also an operator, specifically an operator that should be combined with the previous 
	 * 	one. Supported combined operators are 'less or equals', 'greater or equals' and 
	 * 	'not equals'.
	 * 
	 * 	@return integer of value 0 if operator is one of 'less or equals', 'greater or equals' and 
	 * 		'not equals'
	 * 	@return integer of value 1 if operator is one of 'less', 'greater' or 'equals'
	 * 	@return integer of value -1 if current character is not an operator
	 *
	 */
	private int isOperator() {
		char c = data[currentIndex];
		if(c == '>' || c == '<' || c == '!') {
			if(data[currentIndex + 1] == '=') {
				return 0;
			}
			return 1;
		}
		if(c == '=') return 1;
		return -1;
	}

}
