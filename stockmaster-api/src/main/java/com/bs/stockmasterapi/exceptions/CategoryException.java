package com.bs.stockmasterapi.exceptions;

public class CategoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CategoryException(String exMessage, Exception exception) {
		super(exMessage, exception);
	}

	public CategoryException(String exMessage) {
		super(exMessage);
	}
}
