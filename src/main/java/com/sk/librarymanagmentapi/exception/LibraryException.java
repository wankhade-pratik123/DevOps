package com.sk.librarymanagmentapi.exception;

public class LibraryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LibraryException(String exMessage, Exception exception) {
		super(exMessage, exception);
	}

	public LibraryException(String exMessage) {
		super(exMessage);
	}
}
