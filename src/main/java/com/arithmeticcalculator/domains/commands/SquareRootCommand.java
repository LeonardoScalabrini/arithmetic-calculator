package com.arithmeticcalculator.domains.commands;

import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.domains.interfaces.OperationCommand;

public final class SquareRootCommand implements OperationCommand<Double> {

  private final double radicand;

  private SquareRootCommand(double radicand) {
    this.radicand = radicand;
  }

  public static SquareRootCommand of(double radicand) {
    return new SquareRootCommand(radicand);
  }

  @Override
  public OperationTypes getOperationType() {
    return OperationTypes.SQUARE_ROOT;
  }

  @Override
  public Double execute() throws OperationException {
    if (radicand < 0)
      throw OperationException.withMessage("The radicand should be zero or positive number!");
    return Math.sqrt(radicand);
  }
}
