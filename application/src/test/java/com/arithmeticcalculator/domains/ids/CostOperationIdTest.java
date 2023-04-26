package com.arithmeticcalculator.domains.ids;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.abstracts.UtilsTest;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class CostOperationIdTest extends UtilsTest {

  @Test
  void costOperationId() {
    assertClass(CostOperationId.class, CostOperationId.newInstance());
    var id = CostOperationId.newInstance();
    var uuid = UUID.randomUUID().toString();
    assertNotEquals(id, CostOperationId.newInstance());
    assertEquals(id, CostOperationId.getInstance(id.getId()));
    assertEquals(uuid, CostOperationId.getInstance(uuid).getId());
  }
}
