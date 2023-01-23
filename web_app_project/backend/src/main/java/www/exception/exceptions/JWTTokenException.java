package www.exception.exceptions;

public class JWTTokenException extends Exception {
	
	  private static final long serialVersionUID = 4155493474819544733L;

	public JWTTokenException() {
		  super();
	  }
	  
	  public JWTTokenException(String msg) {
		  super(msg);
	  }
}
