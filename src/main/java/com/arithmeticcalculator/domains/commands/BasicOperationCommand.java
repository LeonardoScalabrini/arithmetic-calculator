package com.arithmeticcalculator.domains.commands;

import com.arithmeticcalculator.domains.BasicOperations;
import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.domains.interfaces.OperationCommand;
import lombok.NonNull;

public final class BasicOperationCommand implements OperationCommand<Double> {
  private final double n1;
  private final double n2;
  private final BasicOperations basicOperations;

  private BasicOperationCommand(@NonNull BasicOperations basicOperations, double n1, double n2) {
    this.n1 = n1;
    this.n2 = n2;
    this.basicOperations = basicOperations;
  }

  public static OperationCommand<Double> of(
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
