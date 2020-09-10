package com.bs.stockmasterapi.exceptions;

public class StockMasterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StockMasterException(String exMessage, Exception exception) {
		super(exMessage, exception);
	}

	public StockMasterException(String exMessage) {
		super(exMessage);
	}
}
