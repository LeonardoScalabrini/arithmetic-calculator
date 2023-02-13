package com.arithmeticcalculator.domains.factories;

import com.arithmeticcalculator.domains.Operations;
import com.arithmeticcalculator.domains.commands.DivisionCommand;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.domains.interfaces.Calculator;
import com.arithmeticcalculator.domains.interfaces.OperationCommand;
import lombok.NonNull;

public final class BasicOperationCommandFactory implements OperationCommand<Double> {
  private final double n1;
  private final double n2;
  private final Calculator calculator;

  private BasicOperationCommandFactory(double n1, double n2, @NonNull Calculator calculator) {
    this.n1 = n1;
    this.n2 = n2;
    this.calculator = calculator;
  }

  public static OperationCommand<Double> of(@NonNull Operations operations, double n1, double n2)
      throws OperationException {
    if (Operations.ADDTION.equals(operations))
      return new BasicOperationCommandFactory(n1, n2, Double::sum);
    if (Operations.SUBTRACTION.equals(operations))
      return new BasicOperationCommandFactory(n1, n2, (v, v2) -> v - v2);
    if (Operations.DIVISION.equals(operations)) return DivisionCommand.of(n1, n2);
    if (Operations.MULTIPLICATION.equals(operations))
      return new BasicOperationCommandFactory(n1, n2, (v, v2) -> v * v2);
    throw OperationException.withMessage("Operation not found!");
  }

  @Override
  public Double execute() throws OperationException {
    return calculator.calc(n1, n2);
  }
}
