package com.arithmeticcalculator.domains;

import com.arithmeticcalculator.domains.ids.CostOperationId;
import lombok.*;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CostOperation {
  CostOperationId costOperationId;
  OperationTypes operationTypes;
  double cost;

  public static CostOperation newInstance(
      @NonNull CostOperationId costOperationId,
      @NonNull OperationTypes operationTypes,
      double cost) {
    return new CostOperation(costOperationId, operationTypes, cost);
  }
}
