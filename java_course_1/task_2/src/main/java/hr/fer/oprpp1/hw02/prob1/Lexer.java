package hr.fer.oprpp1.hw02.prob1;

/**	Class represents lexer which is used to handle string input and
 * 	produce corresponding tokens.
 *  
 * 	@author adrian
 *
 */
public class Lexer {
	/** Stores input in char array. **/
	private char[] data; 
	/** Stores current token. **/
	private Token token; 
	/** Stores current position of char in char array. **/
	private int currentIndex; 
	/** Stores current lexer state. **/
	private LexerState lexerState;
	
	/** Constructor.
	 *	
	 *	@param String input which needs to be handled.
	 */
	public Lexer(String text) {
		data = text.trim().toCharArray();
		currentIndex = 0;
		lexerState = LexerState.BASIC;
	}
	
	
	/** Setter for lexer state.
	 * 	
	 * 	@param LexerState reference which needs to be set
	 * 	@throws NullPointerException if argument is null
	 *
	 */
	public void setState(LexerState state) {
		if(state == null) throw new NullPointerException("Argument state can not be null!");
		lexerState = state;
	}
	
	/** Generates next token in line and returns it.
	 * 	
	 * 	@throws LexerException if no more tokens can be generated
	 *	@throws LexerException if invalid use of excaping is present
	 *	@return next Token in line
	 */
	public Token nextToken() {
		if(token != null && token.getType() == TokenType.EOF) {
			throw new LexerException("No tokens available.");
		}
		skipBlanks();
		
		if(currentIndex >= data.length) {
			token = new Token(TokenType.EOF, null);
			return getToken();
		}
		
		if(lexerState == LexerState.EXTENDED && Character.compare(this.data[currentIndex], '#') != 0) {
			StringBuilder sb = new StringBuilder();
			while(currentIndex < data.length && Character.compare(data[currentIndex], ' ') != 0 && Character.compare(this.data[currentIndex], '#') != 0) {
				sb.append(data[currentIndex]);
				currentIndex++;
			}
			token = new Token(TokenType.WORD, sb.toString());
			return getToken();
		}
		
		if(canBeWord(data[currentIndex])) {
			StringBuilder sb = new StringBuilder();
			while(currentIndex < data.length && canBeWord(data[currentIndex])) {
				if(Character.compare(this.data[currentIndex], '\\') == 0 ) {
					sb.append(data[currentIndex + 1]);
					currentIndex = currentIndex + 2;
				} else {
					sb.append(data[currentIndex]);
					currentIndex++;
				}
			}
			token = new Token(TokenType.WORD, sb.toString());
			return getToken();
		}
		
		if(Character.isDigit(data[currentIndex])) {
			int startIndex = currentIndex;
			currentIndex++;
			while(currentIndex < data.length && Character.isDigit(data[currentIndex])) {
				currentIndex++;
			}
			int endIndex = currentIndex;
			String value = new String(data, startIndex, endIndex - startIndex);
			Long longValue = 0L;
			try {
				longValue = Long.parseLong(value);
			} catch(Exception ex){
				throw new LexerException("Number is not withing Long type range.");
			}
			token = new Token(TokenType.NUMBER, longValue);
			return getToken();
		}
		
		if(Character.compare(this.data[currentIndex], '#') == 0 ) {
			if(lexerState == LexerState.BASIC) {
				lexerState = LexerState.EXTENDED;
			} else lexerState = LexerState.BASIC;
		}
		token = new Token(TokenType.SYMBOL, data[currentIndex++]);
		
		
		return getToken();
	}
	
	/** Getter for the current token. Can be called multiple times with same return.
	 * 	Does not generate new tokens, only return the current.
	 * 	
	 * 	@throws current Token
	 *
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
	
	/** Private method used to determine wether a character could be 
	 * 	classified as TokenType.WORD.
	 * 	
	 * 	@param Character that needs to be evaluated
	 * 	@throws LexerException if there is an invalid use of escaping
	 * 	@return true if character can be part of value for token
	 * 	whose TokenType is WORD, false if not
	 *
	 */
	private boolean canBeWord(char ch) {
		if(Character.isLetter(data[currentIndex])) return true;
		if(Character.compare(ch, '\\') == 0 ) {
			if(this.currentIndex < this.data.length - 1) {
				if(Character.compare(this.data[this.currentIndex + 1], ' ') != 0
						&& !Character.isLetter(this.data[this.currentIndex + 1])) return true;
			} 
			throw new LexerException("Invalid use of escaping!");
		}
		if(this.currentIndex > 0 && Character.isDigit(ch)) {
			if(Character.compare(this.data[this.currentIndex - 1], '\\') == 0 ) return true;
		}
		return false;
	}
}