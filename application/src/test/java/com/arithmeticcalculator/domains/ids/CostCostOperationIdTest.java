package com.arithmeticcalculator.domains.ids;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CostCostOperationIdTest {

  @Test
  void newInstance() {
    var id = CostOperationId.newInstance();
    assertNotNull(id);
    assertNotNull(id.getId());
    assertNotEquals(id, CostOperationId.newInstance());
  }
}
