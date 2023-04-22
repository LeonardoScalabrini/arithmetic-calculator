package com.arithmeticcalculator.domains.commands;

import static com.arithmeticcalculator.domains.exceptions.IllegalStateExceptionFactory.newInstance;

import com.arithmeticcalculator.domains.OperationTypes;

public final class SquareRootCommand implements OperationCommand<Double> {

  private final double radicand;

  private SquareRootCommand(double radicand) {
    if (radicand < 0)
      throw newInstance(getClass())
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
