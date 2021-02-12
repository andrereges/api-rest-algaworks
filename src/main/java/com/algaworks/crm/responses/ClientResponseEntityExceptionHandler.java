package com.algaworks.crm.responses;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.crm.errors.ErrorDetails;
import com.algaworks.crm.exceptions.ClientNotFoundException;

@ControllerAdvice
@RestController
public class ClientResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ClientNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(ClientNotFoundException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		final StringBuffer errors = new StringBuffer();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.append(error.getDefaultMessage());
		}
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), errors.toString(),
				ex.getBindingResult().toString());
		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
