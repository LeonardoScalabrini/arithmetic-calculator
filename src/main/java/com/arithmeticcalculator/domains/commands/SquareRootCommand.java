package com.arithmeticcalculator.domains.commands;

import static com.arithmeticcalculator.domains.exceptions.IllegalStateExceptionFactory.getInstance;

import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.domains.interfaces.OperationCommand;

public final class SquareRootCommand implements OperationCommand<Double> {

  private final double radicand;

  private SquareRootCommand(double radicand) {
    if (radicand < 0)
      throw getInstance()
          .param("radicand", radicand)
          .message("The radicand should be zero or positive number!")
          .build();
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
  public Double execute() {
    return Math.sqrt(radicand);
  }
}
