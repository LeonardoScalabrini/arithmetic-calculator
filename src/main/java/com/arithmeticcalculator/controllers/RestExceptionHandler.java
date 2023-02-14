package com.arithmeticcalculator.controllers;

import com.arithmeticcalculator.domains.exceptions.OperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(OperationException.class)
  protected ResponseEntity<ApiError> handleOperationException(OperationException ex) {
    return ResponseEntity.internalServerError()
        .body(
            ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build());
  }
}
