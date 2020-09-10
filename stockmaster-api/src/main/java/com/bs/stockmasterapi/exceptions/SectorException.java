package com.bs.stockmasterapi.exceptions;

public class SectorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SectorException(String exMessage, Exception exception) {
		super(exMessage, exception);
	}

	public SectorException(String exMessage) {
		super(exMessage);
	}

}
