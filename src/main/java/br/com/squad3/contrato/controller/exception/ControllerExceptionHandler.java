package br.com.squad3.contrato.controller.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

	private StandardError standardError = new StandardError();

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ControllerNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(
			ControllerNotFoundException exception,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		standardError.setTimeStamp(Instant.now());
		standardError.setStatus(status.value());
		standardError.setError("Entity not Found");
		standardError.setMessage(exception.getMessage());
		standardError.setPath(request.getRequestURI());

		return ResponseEntity.status(status).body(this.standardError);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(
			MethodArgumentNotValidException exception,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ValidateError validateError = new ValidateError();
		
		validateError.setTimeStamp(Instant.now());
		validateError.setStatus(status.value());
		validateError.setError("Error Validation");
		validateError.setMessage(exception.getMessage());
		validateError.setPath(request.getRequestURI());

		for (FieldError f : exception.getBindingResult().getFieldErrors()) {
			validateError.addMensagens(f.getField(), f.getDefaultMessage());
		}

		return ResponseEntity.status(status).body(validateError);
	}
	
}
