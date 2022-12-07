package com.example.demo.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		Map<String, String> map = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach(e -> {
			map.put(
				((FieldError)e).getField(),
				e.getDefaultMessage()
			);		
		});
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.BAD_REQUEST);
	
	}
	
}
