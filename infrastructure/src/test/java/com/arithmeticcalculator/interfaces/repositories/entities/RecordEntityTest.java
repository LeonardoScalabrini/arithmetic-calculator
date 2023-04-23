package com.arithmeticcalculator.interfaces.repositories.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.fixtures.Fixture;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class RecordEntityTest {
  @Test
  void build() {
    var user = Fixture.getUserEntity();
    var model = Fixture.getRecord();
    var record = RecordEntity.from(user, model);
    assertNotNull(record.getId());
    assertEquals(model.getDate(), record.getDate());
    assertEquals(model.getAmount(), record.getAmount());
    assertEquals(user.getBalance(), record.getUserBalance());
    assertEquals(model.getOperationTypes(), record.getOperation());
    assertEquals(user, record.getUser());
    assertEquals("2.0", record.getOperationResponse());
    EqualsVerifier.simple()
        .forClass(RecordEntity.class)
        .suppress(Warning.STRICT_INHERITANCE)
        .verify();
    ToStringVerifier.forClass(RecordEntity.class).verify();
  }

  @Test
  void notNull() {
    var userEntity = Fixture.getUserEntity();
    var record = Fixture.getRecord();
    assertThrows(NullPointerException.class, () -> RecordEntity.from(null, record));
    assertThrows(NullPointerException.class, () -> RecordEntity.from(userEntity, null));
  }
}
