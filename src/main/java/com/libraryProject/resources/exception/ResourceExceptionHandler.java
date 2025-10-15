package com.libraryProject.resources.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.libraryProject.exception.AuthenticationFailedException;
import com.libraryProject.exception.NotFoundException;
import com.libraryProject.exception.UnavailableBookException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	
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
	
	public ResponseEntity<ApiError> handleUnavailableBookException(UnavailableBookException ex){
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
 