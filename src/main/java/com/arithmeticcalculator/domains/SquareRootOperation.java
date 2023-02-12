package com.arithmeticcalculator.domains;

import static java.util.Objects.isNull;

import com.arithmeticcalculator.domains.exceptions.CalculatorException;

public final class SquareRootOperation {

  private static SquareRootOperation instance;

  private SquareRootOperation() {}

  public double sqrt(double radicand) throws CalculatorException {
    if (radicand < 0)
      throw CalculatorException.withMessage("The radicand should be zero or positive number!");
    return Math.sqrt(radicand);
  }

  public static SquareRootOperation getInstance() {
    if (isNull(instance)) instance = new SquareRootOperation();
    return instance;
  }
}
