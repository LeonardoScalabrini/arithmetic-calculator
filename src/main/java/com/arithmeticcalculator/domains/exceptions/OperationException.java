package com.arithmeticcalculator.domains.exceptions;

import lombok.NonNull;

public class OperationException extends Exception {

  private OperationException(@NonNull String message) {
    super(message);
  }

  public static OperationException withMessage(String message) {
    return new OperationException(message);
  }
}
