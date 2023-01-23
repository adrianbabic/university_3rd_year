package hr.fer.oprpp1.custom.scripting.lexer;

import java.util.HashSet;
import java.util.Set;

import hr.fer.oprpp1.custom.scripting.elems.*;



public class Lexer {
	/** Field of chars representins source code which is being processed. **/
	private char[] data;
	/** Index of the currently being processed character in field <code>data</code>. **/
	private int currentIndex;
	/** Reference to the Token that was created last. **/
	private Token currentToken;
	/** Storing the current lexer state **/
	private LexerState state;
	private static final Set<Character> operators;
	
	static {
		operators = new HashSet<>();
		operators.add('+');
		operators.add('-');
		operators.add('*');
		operators.add('/');
		operators.add('^');
	}
	
	
	public Lexer(String input) {
		data = input.toCharArray();
		currentIndex = 0;
		currentToken = null;
		state = LexerState.TEXT;
	}
	
	public Token getToken() {
		return currentToken;
	}
	
	public Token nextToken() {
		//if the 'End of file' token was already generated, call of this method is an error
		if(currentToken !=null && currentToken.getTokenType() == TokenType.EOF) 
			throw new LexerException("No tokens available.");
		
		//if there are no more characters left, generate an 'End of file' token
		if(currentIndex >= data.length) {
			currentToken = new Token(TokenType.EOF, null);
			return getToken();
		}
		
		
		if(state == LexerState.TAG) skipBlanks();
		int tmp = isTagSign();
		if(tmp >= 0) {
			currentIndex = currentIndex + 2;
			if(tmp == 0) {
				state = LexerState.TEXT;
				currentToken = new Token(TokenType.TAG_END, new ElementString("$}"));
			} else {
				state = LexerState.TAG;
				currentToken = new Token(TokenType.TAG_START, new ElementString("{$"));
			}
			return getToken();
		}
		
		if(state == LexerState.TEXT) {
			StringBuilder sb = new StringBuilder();
			while(currentIndex < data.length && isTagSign() < 0) {
				if(data[currentIndex] == '\\')
					allowedEscaping();
				sb.append(data[currentIndex++]);
			}
			state = LexerState.TAG;
			currentToken = new Token(TokenType.TEXT, new ElementString(sb.toString()));
			return getToken();
		}
		
		if(state == LexerState.TAG) {
			char first = data[currentIndex]; 
			int startIndex = currentIndex;
			
			if(first == '@' && Character.isLetter(data[currentIndex + 1])) {
				++currentIndex;
				while (Character.isLetterOrDigit(data[currentIndex]) || data[currentIndex] == '_') {
					currentIndex++;
					if(currentIndex >= data.length)
						throw new LexerException("Function expression started but never ended as expected.");
				}
				String value = new String(data, startIndex, currentIndex - startIndex);
				currentToken = new Token(TokenType.FUNCTION, new ElementFunction(value));
				return getToken();
			}
			
			if(first == '"') {
				StringBuilder sb = new StringBuilder();
				sb.append(first);
				++currentIndex;
				while (data[currentIndex] != '"') {
					if(data[currentIndex] == '\\') {
						sb.append(allowedTagStringEscaping());
						currentIndex = currentIndex + 2;
						continue;
					}
					sb.append(data[currentIndex++]);
					if(currentIndex >= data.length)
						throw new LexerException("String literal was opened but never closed.");
				}
				sb.append(data[currentIndex]);
				currentIndex++;
				currentToken = new Token(TokenType.STRING, new ElementString(sb.toString()));
				return getToken();
			}
			
			if(first == '\\')
				throw new LexerException("Invalid use of escaping in tag: escaping in tags can only"
						+ " be used in string literals!");	 
			
			if(Character.isDigit(first) || (first == '-' && Character.isDigit(data[currentIndex + 1]) )) {
				currentToken = new Token(TokenType.NUMBER, extractNumber());
				return getToken();
			}
			
			if(operators.contains(first)) {
				currentToken = new Token(TokenType.OPERATOR, new ElementOperator(
						Character.toString(first)
						));
				currentIndex++;
				return getToken();
			}
			
			if(first == '=') {
				if(getToken().getTokenType() != TokenType.TAG_START)
					throw new LexerException("Character: " + first + " can only be used as a tag name!");
				currentToken = new Token(TokenType.VARIABLE, new ElementVariable(Character.toString(first)));
				currentIndex++;
				return getToken();
			}
			
			if(Character.isLetter(first)) {
				currentIndex++;
				while(currentIndex < data.length && ( Character.isLetterOrDigit(data[currentIndex]) 
						|| data[currentIndex] == '_')) {
					currentIndex++;
				}
				String value = new String(data, startIndex, currentIndex - startIndex);
				currentToken = new Token(TokenType.VARIABLE, new ElementVariable(value.toLowerCase()));
				return getToken();
			}
			
		}
			
		throw new LexerException("Invalid character found: '" + data[currentIndex]+ "'.");
	}
	
	private void skipBlanks() {
		while(currentIndex < data.length) {
			char c = data[currentIndex];
			if(c==' ' || c=='\t' || c=='\r' || c=='\n') {
				currentIndex++;
				continue;
			}
			break;
		}
	}
	
	private int isTagSign() {
		if(currentIndex + 1 >= data.length) return -1;
		char c1 = data[currentIndex];
		char c2 = data[currentIndex + 1];
		if(c1 == '{' && c2 == '$') 
			return 1;
		if(c1 =='$' && c2 == '}')
			return 0;
		return -1;
	}
	
	private Element extractNumber() {
		boolean isNegative = false;
		boolean isDouble = false;
		if(data[currentIndex] == '-') {
			isNegative = true;
			currentIndex++;
		}
		int startIndex = currentIndex;
		while(currentIndex < data.length && Character.isDigit(data[currentIndex])) 
			currentIndex++;

		if(currentIndex < data.length && data[currentIndex] == '.') {
			currentIndex++;
			isDouble = true;
			if(!Character.isDigit(data[currentIndex]))
				throw new LexerException("Digit was expected after the decimal point.");
		}
		
		while(currentIndex < data.length && Character.isDigit(data[currentIndex])) 
			currentIndex++;
		
		String value = new String(data, startIndex, currentIndex - startIndex);
		
		if(isDouble) {
			double num = Double.parseDouble(value);
			if(isNegative) num = -num;
			return new ElementConstantDouble(num);
		} else {
			int num = Integer.parseInt(value);
			if(isNegative) num = -num; 
			return new ElementConstantInteger(num);
		}
	}
	
	private void allowedEscaping() {
		if(currentIndex + 1 >= data.length) 
			throw new LexerException("Invalid use of escaping: " + data[currentIndex]);	
		char c = data[currentIndex + 1];
		if(state == LexerState.TEXT && (c == '\\' || c == '{')) {
			currentIndex++;
			return;
		} else 
			throw new LexerException("Invalid use of escaping: " + data[currentIndex] + c);	
	}
	
	private String allowedTagStringEscaping() {
		if(currentIndex + 1 >= data.length) 
			throw new LexerException("Invalid use of escaping: " + data[currentIndex]);	
		char c = data[currentIndex + 1];
		switch (c) {
		  case '\\':
		    return "\\";
		  case '"':
		    return "\"";
		  case 'n':
		    return "\n";
		  case 'r':
		    return "\r";
		  default:
			  throw new LexerException("Invalid use of escaping: " + data[currentIndex] + c);	
		}
	}
}