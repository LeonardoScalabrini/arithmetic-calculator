package com.arithmeticcalculator.domains;

import static com.arithmeticcalculator.domains.OperationTypes.SQUARE_ROOT;
import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.fixtures.Fixture;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class RecordTest {
  @Test
  void build() {
    var record = Fixture.getRecord();
    assertNotNull(record.getDate());
    assertEquals(5, record.getAmount());
    assertEquals(20, record.getBalance());
    assertEquals(SQUARE_ROOT, record.getOperationTypes());
    assertNotNull(record.getUserId());
    assertEquals(2.0, record.getOperationResult());
    EqualsVerifier.simple().forClass(Record.class).suppress(Warning.STRICT_INHERITANCE).verify();
    ToStringVerifier.forClass(Record.class).verify();
  }

  @Test
  void notNull() {
    assertThrows(NullPointerException.class, () -> Record.<Double>from(null, null, null));
  }
}
