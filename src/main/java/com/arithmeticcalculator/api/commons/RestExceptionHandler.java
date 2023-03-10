package com.arithmeticcalculator.api.commons;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class RestExceptionHandler {

  @ExceptionHandler(IllegalStateException.class)
  private ResponseEntity<ApiError> handleOperationException(IllegalStateException ex) {
    return ResponseEntity.internalServerError()
        .body(
            ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build());
  }
}
