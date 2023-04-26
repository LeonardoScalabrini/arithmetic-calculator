package com.arithmeticcalculator.domains.commands;

import static com.arithmeticcalculator.domains.exceptions.IllegalStateExceptionFactory.builder;

import com.arithmeticcalculator.domains.OperationTypes;
import lombok.Value;

@Value
public class SquareRootCommand implements OperationCommand<Double> {

  double radicand;

  private SquareRootCommand(double radicand) {
    if (radicand < 0)
      throw builder(getClass())
          .param("radicand", radicand)
          .message("The radicand should be zero or positive number!")
          .build();
    this.radicand = radicand;
  }

  public static SquareRootCommand newInstance(double radicand) {
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
