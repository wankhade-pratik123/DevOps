package com.bs.stockmasterapi.exceptions;

import java.time.Instant;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StockMasterExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(
					  value = { StockMasterException.class, 
								StockException.class, 
								SectorException.class,
								CategoryException.class, 
								Exception.class 
							  }
						)
	public ResponseEntity<ErrorDto> handleException(Exception ex) {
		ErrorDto erroDto = ErrorDto.builder()
								   .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR)
								   .errMsg(ex.getLocalizedMessage())
								   .timeStamp(Date.from(Instant.now()))
								   .build();
		return new ResponseEntity<>(erroDto, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
