package com.arithmeticcalculator.interfaces.repositories.jpa;

import static com.arithmeticcalculator.domains.OperationTypes.RANDOM_STRING;
import static com.arithmeticcalculator.fixtures.Fixture.getCostOperationEntity;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class CostOperationEntityJpaRepositoryTest {

  @Autowired private CostOperationEntityJpaRepository costOperationEntityJpaRepository;

  @Test
  void save() {
    var costOperationEntity = getCostOperationEntity();
    var result = costOperationEntityJpaRepository.save(costOperationEntity);
    assertEquals(costOperationEntity, result);
  }

  @Test
  void findByType() {
    var costOperationEntity = getCostOperationEntity();
    costOperationEntityJpaRepository.save(costOperationEntity);
    var result = costOperationEntityJpaRepository.findByType(costOperationEntity.getType());
    assertEquals(costOperationEntity, result.orElseThrow());
    assertTrue(costOperationEntityJpaRepository.findByType(RANDOM_STRING).isEmpty());
  }
}
