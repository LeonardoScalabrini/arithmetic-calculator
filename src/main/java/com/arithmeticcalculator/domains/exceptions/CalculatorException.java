package com.arithmeticcalculator.domains.exceptions;

import lombok.NonNull;

public class CalculatorException extends Exception {

  private CalculatorException(@NonNull String message) {
    super(message);
  }

  public static CalculatorException withMessage(String message) {
    return new CalculatorException(message);
  }
}
