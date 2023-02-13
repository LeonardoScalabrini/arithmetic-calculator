package com.arithmeticcalculator.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.domains.Operations;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class OperationEntityTest {

  @Test
  void build() {
    var operationEntity = OperationEntity.builder().cost(5).type(Operations.DIVISION).build();
    assertNotNull(operationEntity.getId());
    assertEquals(5, operationEntity.getCost());
    assertEquals(Operations.DIVISION, operationEntity.getType());
    EqualsVerifier.forClass(OperationEntity.class).suppress(Warning.STRICT_INHERITANCE).verify();
    ToStringVerifier.forClass(OperationEntity.class).verify();
  }

  @Test
  void notNull() {
    assertThrows(
        NullPointerException.class, () -> OperationEntity.builder().type(null).cost(5).build());
  }
}
