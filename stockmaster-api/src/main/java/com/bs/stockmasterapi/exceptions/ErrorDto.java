package com.bs.stockmasterapi.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
	private String errMsg;
	private Date timeStamp;
	private HttpStatus httpStatusCode;
}
