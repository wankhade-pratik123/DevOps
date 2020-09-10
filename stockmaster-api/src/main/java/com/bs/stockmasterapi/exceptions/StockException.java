package com.bs.stockmasterapi.exceptions;

public class StockException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public StockException(String exMessage, Exception exception) {
		super(exMessage, exception);
	}

	public StockException(String exMessage) {
		super(exMessage);
	}
}
