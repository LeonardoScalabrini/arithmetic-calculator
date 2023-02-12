package com.arithmeticcalculator.domains.exceptions;

public class CalculatorException extends Exception {

  private CalculatorException(String message) {
    super(message);
  }

  public static CalculatorException withMessage(String message) {
    return new CalculatorException(message);
  }
}
