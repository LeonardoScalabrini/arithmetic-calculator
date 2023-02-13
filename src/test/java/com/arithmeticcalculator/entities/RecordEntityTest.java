package com.arithmeticcalculator.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.fixtures.Fixture;
import com.jparams.verifier.tostring.ToStringVerifier;
import java.util.Date;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class RecordEntityTest {

  private final Date date = new Date();

  @Test
  void build() {
    var record = Fixture.getRecordEntity();
    assertNotNull(record.getId());
    assertEquals(Fixture.DATE, record.getDate());
    assertEquals(5, record.getAmount());
    assertEquals(20, record.getUserBalance());
    assertEquals(Fixture.getOperationEntity(), record.getOperation());
    assertEquals(Fixture.getUserEntity(), record.getUser());
    assertEquals("2.0", record.getOperationResponse());
    EqualsVerifier.simple()
        .forClass(RecordEntity.class)
        .suppress(Warning.STRICT_INHERITANCE)
        .verify();
    ToStringVerifier.forClass(RecordEntity.class).verify();
  }

  @Test
  void notNull() {
    assertThrows(NullPointerException.class, () -> RecordEntity.builder().build());
  }
}
