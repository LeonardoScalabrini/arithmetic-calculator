package com.arithmeticcalculator.repositories;

import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.entities.OperationEntity;
import com.arithmeticcalculator.entities.RecordEntity;
import com.arithmeticcalculator.entities.UserEntity;
import com.arithmeticcalculator.fixtures.Fixture;
import com.arithmeticcalculator.repositories.jpa.OperationEntityJpaRepository;
import com.arithmeticcalculator.repositories.jpa.RecordEntityJpaRepository;
import com.arithmeticcalculator.repositories.jpa.UserEntityJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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

  private final Record<Double> record = Fixture.getRecord();
  private final UserEntity userEntity = Fixture.getUserEntity();
  private final OperationEntity operationEntity = Fixture.getOperationEntity();

  private final RecordEntity recordEntity = Fixture.getRecordEntity();

  @BeforeEach
  void setUp() {
    Mockito.when(userEntityJpaRepository.findByEmail(ArgumentMatchers.anyString()))
        .thenReturn(Optional.of(userEntity));
    Mockito.when(operationEntityJpaRepository.findByType(OperationTypes.SQUARE_ROOT))
        .thenReturn(Optional.of(operationEntity));
    Mockito.when(recordEntityJpaRepository.save(recordEntity)).thenReturn(recordEntity);
  }

  @Test
  void save() {
    recordRepository.save(record);
    Mockito.verify(userEntityJpaRepository, Mockito.times(1)).findByEmail("email");
    Mockito.verify(operationEntityJpaRepository, Mockito.times(1))
        .findByType(OperationTypes.SQUARE_ROOT);
    Mockito.verify(recordEntityJpaRepository, Mockito.times(1)).save(Mockito.any());
  }

  @Test
  void notFoundUser() {
    Mockito.when(userEntityJpaRepository.findByEmail(ArgumentMatchers.anyString()))
        .thenReturn(Optional.empty());
    recordRepository.save(record);
    Mockito.verify(userEntityJpaRepository, Mockito.times(1)).findByEmail("email");
    Mockito.verify(operationEntityJpaRepository, Mockito.times(0))
        .findByType(OperationTypes.SQUARE_ROOT);
    Mockito.verify(recordEntityJpaRepository, Mockito.times(0)).save(Mockito.any());
  }

  @Test
  void notFoundOperation() {
    Mockito.when(operationEntityJpaRepository.findByType(OperationTypes.SQUARE_ROOT))
        .thenReturn(Optional.empty());
    recordRepository.save(record);
    Mockito.verify(userEntityJpaRepository, Mockito.times(1)).findByEmail("email");
    Mockito.verify(operationEntityJpaRepository, Mockito.times(1))
        .findByType(OperationTypes.SQUARE_ROOT);
    Mockito.verify(recordEntityJpaRepository, Mockito.times(0)).save(Mockito.any());
  }
}
