package com.arithmeticcalculator.domains.ids;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class CostOperationId extends Id {
  private CostOperationId() {}

  public static CostOperationId newInstance() {
    return new CostOperationId();
  }
}
