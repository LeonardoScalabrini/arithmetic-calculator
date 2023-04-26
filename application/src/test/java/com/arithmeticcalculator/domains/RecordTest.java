package com.arithmeticcalculator.domains;

import static com.arithmeticcalculator.domains.OperationTypes.SQUARE_ROOT;
import static com.arithmeticcalculator.fixtures.Fixture.*;
import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.abstracts.UtilsTest;
import com.arithmeticcalculator.fixtures.Fixture;
import org.junit.jupiter.api.Test;

class RecordTest extends UtilsTest {
  @Test
  void record() {
    assertClass(Record.class, getRecord());
    var user = getUser();
    var record = Fixture.getRecord(user, getCostOperation());
    assertNotNull(record.getDate());
    assertNotNull(record.getRecordId());
    assertEquals(user.getUserId(), record.getUserId());
    assertEquals(SQUARE_ROOT, record.getOperationTypes());
    assertEquals(5, record.getAmount());
    assertEquals(20, record.getBalance());
    assertEquals(2.0, record.getOperationResult());
  }
}
