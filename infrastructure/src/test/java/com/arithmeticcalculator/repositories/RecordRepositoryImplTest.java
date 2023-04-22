package com.arithmeticcalculator.repositories;

import static com.arithmeticcalculator.domains.OperationTypes.SQUARE_ROOT;
import static com.arithmeticcalculator.fixtures.Fixture.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.entities.OperationEntity;
import com.arithmeticcalculator.entities.RecordEntity;
import com.arithmeticcalculator.entities.UserEntity;
import com.arithmeticcalculator.repositories.jpa.OperationEntityJpaRepository;
import com.arithmeticcalculator.repositories.jpa.RecordEntityJpaRepository;
import com.arithmeticcalculator.repositories.jpa.UserEntityJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RecordRepositoryImplTest {

  @Mock private UserEntityJpaRepository userEntityJpaRepository;
  @Mock private OperationEntityJpaRepository operationEntityJpaRepository;
  @Mock private RecordEntityJpaRepository recordEntityJpaRepository;
  @InjectMocks private RecordRepositoryImpl recordRepository;

  private final Record<Double> record = getRecord();
  private final UserEntity userEntity = getUserEntity();
  private final OperationEntity operationEntity = getOperationEntity();

  private final RecordEntity recordEntity = getRecordEntity();

  @BeforeEach
  void setUp() {
    when(userEntityJpaRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));
    when(operationEntityJpaRepository.findByType(SQUARE_ROOT))
        .thenReturn(Optional.of(operationEntity));
    when(recordEntityJpaRepository.save(recordEntity)).thenReturn(recordEntity);
  }

  @Test
  void save() {
    recordRepository.save(record);
    verify(userEntityJpaRepository, times(1)).findByEmail("email");
    verify(operationEntityJpaRepository, times(1)).findByType(SQUARE_ROOT);
    verify(recordEntityJpaRepository, times(1)).save(Mockito.any());
  }

  @Test
  void notFoundUser() {
    when(userEntityJpaRepository.findByEmail(anyString())).thenReturn(Optional.empty());
    recordRepository.save(record);
    verify(userEntityJpaRepository, times(1)).findByEmail("email");
    verify(operationEntityJpaRepository, times(0)).findByType(SQUARE_ROOT);
    verify(recordEntityJpaRepository, times(0)).save(Mockito.any());
  }

  @Test
  void notFoundOperation() {
    when(operationEntityJpaRepository.findByType(SQUARE_ROOT)).thenReturn(Optional.empty());
    recordRepository.save(record);
    verify(userEntityJpaRepository, times(1)).findByEmail("email");
    verify(operationEntityJpaRepository, times(1)).findByType(SQUARE_ROOT);
    verify(recordEntityJpaRepository, times(0)).save(any());
  }
}
