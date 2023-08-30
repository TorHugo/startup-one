package com.dev.startupone.lib.exception;

import com.dev.startupone.lib.exception.impl.DataBaseException;
import com.dev.startupone.lib.exception.impl.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

	static private HttpStatus notFound = HttpStatus.NOT_FOUND;
	static private HttpStatus badRequest = HttpStatus.BAD_REQUEST;
	static private HttpStatus unProcessable = HttpStatus.UNPROCESSABLE_ENTITY;


	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException exception,
                                                        HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(notFound.value());
		err.setError("Resource not found.");
		err.setMessage(exception.getMessage());
		err.setRelativePath(request.getRequestURI());

		return ResponseEntity.status(notFound).body(err);
	}

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataBase(DataBaseException exception,
														HttpServletRequest request){
		StandardError err = new StandardError();

		err.setTimestamp(Instant.now());
		err.setStatus(badRequest.value());
		err.setError("DataBase exception.");
		err.setMessage(exception.getMessage());
		err.setRelativePath(request.getRequestURI());
		err.setCorrectPath("/api/v1/auth/authenticate");

		return ResponseEntity.status(badRequest).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException exception,
                                                      HttpServletRequest request){
		ValidationError err = new ValidationError();

		err.setTimestamp(Instant.now());
		err.setStatus(unProcessable.value());
		err.setError("Validation exception.");
		err.setMessage(exception.getMessage());
		err.setRelativePath(request.getRequestURI());

		for (FieldError error : exception.getBindingResult().getFieldErrors()){
			err.addError(error.getField(), error.getDefaultMessage());
		}

		return ResponseEntity.status(unProcessable).body(err);
	}
}
