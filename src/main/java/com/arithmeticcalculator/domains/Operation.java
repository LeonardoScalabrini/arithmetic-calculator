package com.arithmeticcalculator.domains;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@Value
public class Operation {
  Operations operations;
  double cost;

  private Operation(@NonNull Operations operations, double cost) {
    this.operations = operations;
    this.cost = cost;
  }
}
