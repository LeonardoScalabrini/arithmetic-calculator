package com.arithmeticcalculator.domains.ids;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class CostOperationId extends Id {
  private CostOperationId() {
    super();
  }

  private CostOperationId(String id) {
    super(id);
  }

  public static CostOperationId newInstance() {
    return new CostOperationId();
  }

  public static CostOperationId getInstance(String id) {
    return new CostOperationId(id);
  }
}
