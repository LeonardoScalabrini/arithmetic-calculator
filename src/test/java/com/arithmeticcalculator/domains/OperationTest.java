package com.arithmeticcalculator.domains;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.fixtures.Fixture;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class OperationTest {

  @Test
  void build() {
    var operation = Fixture.getOperation();
    assertEquals(Operations.SQUARE_ROOT, operation.getOperations());
    assertEquals(5, operation.getCost());
    EqualsVerifier.forClass(Operation.class).suppress(Warning.STRICT_INHERITANCE).verify();
    ToStringVerifier.forClass(Operation.class).verify();
  }

  @Test
  void notNull() {
    assertThrows(NullPointerException.class, () -> Operation.builder().build());
  }
}
