package com.arithmeticcalculator.domains.ids;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RecordIdTest {

  @Test
  void newInstance() {
    var id = RecordId.newInstance();
    assertNotNull(id);
    assertNotNull(id.getId());
    assertNotEquals(id, RecordId.newInstance());
  }
}
