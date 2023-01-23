package www.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import www.exception.exceptions.IdNotFoundException;

@ControllerAdvice
public class InstruktorExceptionHandler {

	@ExceptionHandler(value = IdNotFoundException.class)
	public ResponseEntity<Object> hadnleIdNotFoundException(IdNotFoundException ind) {
		return new ResponseEntity<Object> (ind.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
