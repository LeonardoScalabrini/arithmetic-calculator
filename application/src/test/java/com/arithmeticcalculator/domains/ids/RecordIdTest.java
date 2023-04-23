package com.arithmeticcalculator.domains.ids;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class RecordIdTest {

  @Test
  void newInstance() {
    var id = RecordId.newInstance();
    assertNotNull(id);
    assertNotNull(id.getId());
    assertNotEquals(id, RecordId.newInstance());
  }

  @Test
  void getInstance() {
    var id = UUID.randomUUID().toString();
    var instance = RecordId.getInstance(id).getId();
    assertEquals(id, instance);
  }
}
