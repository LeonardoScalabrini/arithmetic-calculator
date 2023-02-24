package com.arithmeticcalculator.domains;

import static com.arithmeticcalculator.domains.OperationTypes.*;

import com.arithmeticcalculator.domains.commands.BasicOperationCommand;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.domains.interfaces.OperationCommand;
import java.util.function.DoubleBinaryOperator;

public enum BasicOperations implements DoubleBinaryOperator {
  PLUS(ADDITION, Double::sum),
  MINUS(SUBTRACTION, (n1, n2) -> n1 - n2),
  TIMES(MULTIPLICATION, (n1, n2) -> n1 * n2),
  DIVIDE(DIVISION, BasicOperations::divide);

  private final OperationTypes operationType;
  private final DoubleBinaryOperator doubleBinaryOperator;

  BasicOperations(OperationTypes operationType, DoubleBinaryOperator doubleBinaryOperator) {
    this.operationType = operationType;
    this.doubleBinaryOperator = doubleBinaryOperator;
  }

  private static double divide(double dividend, double divisor) throws OperationException {
    if (divisor == 0)
      throw OperationException.withMessage("The divisor should be great than zero!");
    return dividend / divisor;
  }

  public OperationCommand<Double> getOperationCommand(double n1, double n2) {
    return BasicOperationCommand.of(this, n1, n2);
  }

  public OperationTypes getOperationType() {
    return operationType;
  }

  @Override
  public double applyAsDouble(double n1, double n2) {
    return doubleBinaryOperator.applyAsDouble(n1, n2);
  }
}
