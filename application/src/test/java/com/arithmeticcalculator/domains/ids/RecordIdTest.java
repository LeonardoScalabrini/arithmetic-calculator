package com.arithmeticcalculator.domains.ids;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.abstracts.UtilsTest;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class RecordIdTest extends UtilsTest {

  @Test
  void recordId() {
    assertClass(RecordId.class, RecordId.newInstance());
    var id = RecordId.newInstance();
    var uuid = UUID.randomUUID().toString();
    assertNotEquals(id, RecordId.newInstance());
    assertEquals(id, RecordId.getInstance(id.getId()));
    assertEquals(uuid, RecordId.getInstance(uuid).getId());
  }
}
