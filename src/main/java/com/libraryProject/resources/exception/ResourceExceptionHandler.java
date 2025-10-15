package com.libraryProject.resources.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.libraryProject.exception.AuthenticationFailedException;
import com.libraryProject.exception.NotFoundException;
import com.libraryProject.exception.UnavailableBookException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex){
		
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(AuthenticationFailedException.class)
	public ResponseEntity<ApiError> handleAuthenticationFailedException(AuthenticationFailedException ex){
		
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(UnavailableBookException.class)
	public ResponseEntity<ApiError> handleUnavailableBookException(UnavailableBookException ex){
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	
	// retornar mensagem de erro customizada (validação dados de entrada)
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<String> errors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach( error -> {
			errors.add(error.getDefaultMessage());
		});
				
		ApiErrorList error = new ApiErrorList(HttpStatus.BAD_REQUEST.value(), 
				"Invalid field(s)", LocalDateTime.now(), errors); // mensagem: default message
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
	}
	
}
 