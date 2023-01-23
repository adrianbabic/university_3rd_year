package www.exception.exceptions;

public class IdNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 6740890816441567185L;
	
	public IdNotFoundException() {
		super();
	}
	
	public IdNotFoundException(String message) {
		super(message);
	}

}
