package www.exception;

public class NoSuchStudentException extends Exception{
	
	public NoSuchStudentException() {
		super();
	}
	
	public NoSuchStudentException(String msg) {
		super(msg);
	}

}
