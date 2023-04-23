package com.arithmeticcalculator.domains.ids;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class CostOperationIdTest {

  @Test
  void newInstance() {
    var id = CostOperationId.newInstance();
    assertNotNull(id);
    assertNotNull(id.getId());
    assertNotEquals(id, CostOperationId.newInstance());
  }

  @Test
  void getInstance() {
    var id = UUID.randomUUID().toString();
    var instance = CostOperationId.getInstance(id).getId();
    assertEquals(id, instance);
  }
}
