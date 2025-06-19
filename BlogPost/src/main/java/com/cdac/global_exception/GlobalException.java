package com.cdac.global_exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cdac.Dto.ApiResponse;
import com.cdac.custom_exception.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalException {
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException e){
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
}
@ExceptionHandler(BadCredentialsException.class)
public ResponseEntity<?> badCredentialsExceptionHandler(BadCredentialsException e){
	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(e.getMessage()));
}
}
