package com.libraryProject.exception;

public class UnavailableBookException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UnavailableBookException(String msg) {
		super(msg);
	}
}
