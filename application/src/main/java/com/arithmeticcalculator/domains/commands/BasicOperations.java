package com.arithmeticcalculator.domains.commands;

import static com.arithmeticcalculator.domains.OperationTypes.*;
import static com.arithmeticcalculator.domains.exceptions.IllegalStateExceptionFactory.builder;

import com.arithmeticcalculator.domains.OperationTypes;
import java.util.function.DoubleBinaryOperator;
import lombok.NonNull;

public enum BasicOperations implements DoubleBinaryOperator {
  PLUS(ADDITION, Double::sum),
  MINUS(SUBTRACTION, (n1, n2) -> n1 - n2),
  TIMES(MULTIPLICATION, (n1, n2) -> n1 * n2),
  DIVIDE(DIVISION, BasicOperations::divide);

  private final OperationTypes operationType;
  private final DoubleBinaryOperator doubleBinaryOperator;

  BasicOperations(
      @NonNull OperationTypes operationType, @NonNull DoubleBinaryOperator doubleBinaryOperator) {
    this.operationType = operationType;
    this.doubleBinaryOperator = doubleBinaryOperator;
  }

  private static double divide(double dividend, double divisor) {
    if (divisor == 0)
      throw builder(BasicOperations.class)
          .param("dividend", Double.toString(divisor))
          .message("The divisor should be great than zero!")
          .build();
    return dividend / divisor;
  }

  public OperationCommand<Double> getOperationCommand(double n1, double n2) {
    return BasicOperationCommand.newInstance(this, n1, n2);
  }

  public OperationTypes getOperationType() {
    return operationType;
  }

  @Override
  public double applyAsDouble(double n1, double n2) {
    return doubleBinaryOperator.applyAsDouble(n1, n2);
  }
}
