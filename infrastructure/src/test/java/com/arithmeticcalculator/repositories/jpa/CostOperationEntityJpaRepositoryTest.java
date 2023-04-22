package com.arithmeticcalculator.repositories.jpa;

import static com.arithmeticcalculator.domains.OperationTypes.RANDOM_STRING;
import static com.arithmeticcalculator.fixtures.Fixture.getOperationEntity;
import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.entities.OperationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CostOperationEntityJpaRepositoryTest {

  @Autowired private OperationEntityJpaRepository operationEntityJpaRepository;

  @Test
  void save() {
    var operationEntity = getOperationEntity();
    var result = operationEntityJpaRepository.save(operationEntity);
    assertEquals(operationEntity, result);
  }

  @Test
  void saveTypeNull() {
    var operationEntity = new OperationEntity();
    assertThrows(RuntimeException.class, () -> operationEntityJpaRepository.save(operationEntity));
  }

  @Test
  void findByType() {
    var operationEntity = getOperationEntity();
    operationEntityJpaRepository.save(operationEntity);
    var result = operationEntityJpaRepository.findByType(operationEntity.getType());
    assertEquals(operationEntity, result.orElseThrow());
    assertTrue(operationEntityJpaRepository.findByType(RANDOM_STRING).isEmpty());
  }
}
