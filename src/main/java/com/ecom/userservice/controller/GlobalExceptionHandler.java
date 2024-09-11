package com.ecom.userservice.controller;

import com.ecom.userservice.common.ErrorMessage;
import com.ecom.userservice.common.exceptions.DuplicateUserFoundException;
import com.ecom.userservice.common.exceptions.UserNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Map<String, String>> handleResourceNotFoundException(UserNotFoundException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
    ErrorMessage errorMessage = new ErrorMessage();
    errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage())
    );
    errorMessage.setErrorDetails(errors);
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ExceptionHandler(DuplicateUserFoundException.class)
  public ResponseEntity<ErrorMessage> handleDuplicateUserExceptions(DuplicateUserFoundException ex) {
    ErrorMessage errorMessage = new ErrorMessage();
    errorMessage.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
    Map<String, String> errors = new HashMap<>();
    errors.put("error", ex.getMessage());
    errorMessage.setErrorDetails(errors);
    return new ResponseEntity<>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
  }

}
