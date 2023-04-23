package com.arithmeticcalculator.interfaces.repositories.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.fixtures.Fixture;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class CostOperationEntityTest {

  @Test
  void build() {
    var operationEntity = Fixture.getCostOperationEntity();
    assertNotNull(operationEntity.getId());
    assertEquals(5, operationEntity.getCost());
    assertEquals(OperationTypes.SQUARE_ROOT, operationEntity.getType());
    EqualsVerifier.forClass(CostOperationEntity.class)
        .suppress(Warning.STRICT_INHERITANCE)
        .verify();
    ToStringVerifier.forClass(CostOperationEntity.class).verify();
  }

  @Test
  void notNull() {
    assertThrows(NullPointerException.class, () -> CostOperationEntity.from(null));
  }
}
