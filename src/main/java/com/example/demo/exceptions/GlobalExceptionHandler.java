package com.example.demo.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.payloads.APIResponses;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponses> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		APIResponses apiResp = new APIResponses(ex.getMessage(), false, LocalDateTime.now());
		return new ResponseEntity<APIResponses>(apiResp, HttpStatus.NOT_FOUND);
	}
	
}
