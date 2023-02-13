package com.arithmeticcalculator.domains.commands;

import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.domains.interfaces.OperationCommand;

public final class DivisionCommand implements OperationCommand<Double> {

  private final double dividend;
  private final double divisor;

  private DivisionCommand(double dividend, double divisor) {
    this.dividend = dividend;
    this.divisor = divisor;
  }

  public static DivisionCommand of(double dividend, double divisor) {
    return new DivisionCommand(dividend, divisor);
  }

  public Double execute() throws OperationException {
    if (divisor == 0)
      throw OperationException.withMessage("The divisor should be great than zero!");
    return dividend / divisor;
  }
}
