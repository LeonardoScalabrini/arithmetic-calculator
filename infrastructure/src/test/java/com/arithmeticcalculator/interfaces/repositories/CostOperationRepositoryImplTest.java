package com.arithmeticcalculator.interfaces.repositories;

import static com.arithmeticcalculator.fixtures.Fixture.getCostOperationEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.interfaces.repositories.entities.CostOperationEntity;
import com.arithmeticcalculator.interfaces.repositories.jpa.CostOperationEntityJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CostOperationRepositoryImplTest {

  @Mock private CostOperationEntityJpaRepository costOperationEntityJpaRepository;
  @InjectMocks private CostOperationRepositoryImpl operationRepository;

  private final CostOperationEntity costOperationEntity = getCostOperationEntity();

  @BeforeEach
  void setUp() {
    when(costOperationEntityJpaRepository.findByType(OperationTypes.DIVISION))
        .thenReturn(Optional.of(costOperationEntity));
  }

  @Test
  void findByName() {
    var result = operationRepository.findByName(OperationTypes.DIVISION).orElseThrow();
    assertEquals(costOperationEntity.getType(), result.getOperationTypes());
    assertEquals(costOperationEntity.getCost(), result.getCost());
    verify(costOperationEntityJpaRepository, times(1)).findByType(OperationTypes.DIVISION);
  }

  @Test
  void empty() {
    when(costOperationEntityJpaRepository.findByType(OperationTypes.DIVISION))
        .thenReturn(Optional.empty());
    var result = operationRepository.findByName(OperationTypes.DIVISION);
    assertTrue(result.isEmpty());
    verify(costOperationEntityJpaRepository, times(1)).findByType(OperationTypes.DIVISION);
  }
}
