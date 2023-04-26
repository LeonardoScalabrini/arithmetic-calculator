package com.arithmeticcalculator.domains.commands;

import com.arithmeticcalculator.domains.OperationTypes;
import lombok.NonNull;
import lombok.Value;

@Value
public class BasicOperationCommand implements OperationCommand<Double> {
  double n1;
  double n2;
  BasicOperations basicOperations;

  private BasicOperationCommand(@NonNull BasicOperations basicOperations, double n1, double n2) {
    this.n1 = n1;
    this.n2 = n2;
    this.basicOperations = basicOperations;
  }

  public static BasicOperationCommand newInstance(
      @NonNull BasicOperations basicOperations, double n1, double n2) {
    return new BasicOperationCommand(basicOperations, n1, n2);
  }

  @Override
  public OperationTypes getOperationType() {
    return basicOperations.getOperationType();
  }

  @Override
  public Double execute() {
    return basicOperations.applyAsDouble(n1, n2);
  }
}
