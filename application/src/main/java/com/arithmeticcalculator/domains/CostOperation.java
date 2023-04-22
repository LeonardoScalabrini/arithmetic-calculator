package com.arithmeticcalculator.domains;

import com.arithmeticcalculator.domains.ids.CostOperationId;
import lombok.*;

@Value
@Builder
public class CostOperation {
  CostOperationId costOperationId;
  OperationTypes operationTypes;
  double cost;

  private CostOperation(
      @NonNull CostOperationId costOperationId,
      @NonNull OperationTypes operationTypes,
      double cost) {
    this.costOperationId = costOperationId;
    this.operationTypes = operationTypes;
    this.cost = cost;
  }
}
