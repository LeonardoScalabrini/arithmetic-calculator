package com.arithmeticcalculator.repositories;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.domains.Operations;
import com.arithmeticcalculator.entities.OperationEntity;
import com.arithmeticcalculator.repositories.jpa.OperationEntityJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class OperationRepositoryImplTest {

  @Mock private OperationEntityJpaRepository operationEntityJpaRepository;
  @InjectMocks private OperationRepositoryImpl operationRepository;

  private final OperationEntity operationEntity =
      OperationEntity.builder().type(Operations.SQUARE_ROOT).cost(50).build();

  @BeforeEach
  void setUp() {
    Mockito.when(operationEntityJpaRepository.findByType(Operations.SQUARE_ROOT))
        .thenReturn(Optional.of(operationEntity));
  }

  @Test
  void findByName() {
    var result = operationRepository.findByName(Operations.SQUARE_ROOT).orElseThrow();
    assertEquals(operationEntity.getType(), result.getOperations());
    assertEquals(operationEntity.getCost(), result.getCost());
    Mockito.verify(operationEntityJpaRepository, Mockito.times(1))
        .findByType(Operations.SQUARE_ROOT);
  }

  @Test
  void empty() {
    Mockito.when(operationEntityJpaRepository.findByType(Operations.SQUARE_ROOT))
        .thenReturn(Optional.empty());
    var result = operationRepository.findByName(Operations.SQUARE_ROOT);
    assertTrue(result.isEmpty());
    Mockito.verify(operationEntityJpaRepository, Mockito.times(1))
        .findByType(Operations.SQUARE_ROOT);
  }
}
