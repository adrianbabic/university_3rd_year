package www.exception.exceptions;

public class TokenExpiredException extends Exception {

	private static final long serialVersionUID = 8095810323525307289L;

	public TokenExpiredException() {
		super();
	}
	
	public TokenExpiredException(String msg) {
		super(msg);
	}
	
}
