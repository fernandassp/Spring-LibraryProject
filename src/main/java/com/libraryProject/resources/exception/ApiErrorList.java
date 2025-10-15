package com.libraryProject.resources.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiErrorList extends ApiError {  // retornar todos os erros para o usuário, ao invés de só o primeiro

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> errors;
	
	public ApiErrorList(int code, String msg, LocalDateTime date, List<String> errors) {
		super(code, msg, date);
		this.errors = errors;
	}

}
