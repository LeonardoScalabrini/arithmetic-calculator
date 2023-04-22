package com.arithmeticcalculator.repositories;

import static com.arithmeticcalculator.fixtures.Fixture.getOperationEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.entities.OperationEntity;
import com.arithmeticcalculator.repositories.jpa.OperationEntityJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CostOperationRepositoryImplTest {

  @Mock private OperationEntityJpaRepository operationEntityJpaRepository;
  @InjectMocks private CostOperationRepositoryImpl operationRepository;

  private final OperationEntity operationEntity = getOperationEntity();

  @BeforeEach
  void setUp() {
    when(operationEntityJpaRepository.findByType(OperationTypes.DIVISION))
        .thenReturn(Optional.of(operationEntity));
  }

  @Test
  void findByName() {
    var result = operationRepository.findByName(OperationTypes.DIVISION).orElseThrow();
    assertEquals(operationEntity.getType(), result.getOperationTypes());
    assertEquals(operationEntity.getCost(), result.getCost());
    verify(operationEntityJpaRepository, times(1)).findByType(OperationTypes.DIVISION);
  }

  @Test
  void empty() {
    when(operationEntityJpaRepository.findByType(OperationTypes.DIVISION))
        .thenReturn(Optional.empty());
    var result = operationRepository.findByName(OperationTypes.DIVISION);
    assertTrue(result.isEmpty());
    verify(operationEntityJpaRepository, times(1)).findByType(OperationTypes.DIVISION);
  }
}
