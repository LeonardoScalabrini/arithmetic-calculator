package com.arithmeticcalculator.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.arithmeticcalculator.fixtures.Fixture;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class CostOperationTest {

  @Test
  void build() {
    var operation = Fixture.getCostOperation();
    assertEquals(OperationTypes.SQUARE_ROOT, operation.getOperationTypes());
    assertEquals(5, operation.getCost());
    EqualsVerifier.forClass(CostOperation.class).suppress(Warning.STRICT_INHERITANCE).verify();
    ToStringVerifier.forClass(CostOperation.class).verify();
  }

  @Test
  void notNull() {
    assertThrows(NullPointerException.class, () -> CostOperation.builder().cost(5).build());
  }
}
