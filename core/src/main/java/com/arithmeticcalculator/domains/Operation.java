package com.arithmeticcalculator.domains;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@Value
public class Operation {
  OperationTypes operationTypes;
  double cost;

  private Operation(@NonNull OperationTypes operationTypes, double cost) {
    this.operationTypes = operationTypes;
    this.cost = cost;
  }
}
