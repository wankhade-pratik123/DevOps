package com.sk.librarymanagmentapi.exception;

public class BookException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookException(String exMessage, Exception exception) {
		super(exMessage, exception);
	}

	public BookException(String exMessage) {
		super(exMessage);
	}
}
