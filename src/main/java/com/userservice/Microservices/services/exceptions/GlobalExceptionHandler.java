package com.userservice.Microservices.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.userservice.Microservices.services.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResponseNotFoundException(ResourceNotFoundException ex)
	{
		String message = ex.getMessage();
		
		ApiResponse.builder().message(message).status(HttpStatus.NOT_FOUND).build();
		return null;

	}
}
