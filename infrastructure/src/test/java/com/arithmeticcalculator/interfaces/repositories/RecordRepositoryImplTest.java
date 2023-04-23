package com.arithmeticcalculator.interfaces.repositories;

import static com.arithmeticcalculator.fixtures.Fixture.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.interfaces.repositories.entities.RecordEntity;
import com.arithmeticcalculator.interfaces.repositories.entities.UserEntity;
import com.arithmeticcalculator.interfaces.repositories.jpa.RecordEntityJpaRepository;
import com.arithmeticcalculator.interfaces.repositories.jpa.UserEntityJpaRepository;
import com.arithmeticcalculator.ports.out.RecordRepositoryInterface;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RecordRepositoryImplTest {

  @Mock private UserEntityJpaRepository userEntityJpaRepository;
  @Mock private RecordEntityJpaRepository recordEntityJpaRepository;
  private RecordRepositoryInterface recordRepository;

  private final Record<Double> record = getRecord();
  private final UserEntity userEntity = getUserEntity();

  private final RecordEntity recordEntity = getRecordEntity();

  @BeforeEach
  void setUp() {
    recordRepository = new RecordRepositoryImpl(userEntityJpaRepository, recordEntityJpaRepository);
    when(userEntityJpaRepository.findById(anyString())).thenReturn(Optional.of(userEntity));
    when(recordEntityJpaRepository.save(recordEntity)).thenReturn(recordEntity);
  }

  @Test
  void save() {
    recordRepository.save(record);
    verify(userEntityJpaRepository, times(1)).findById(record.getUserId().getId());
    verify(recordEntityJpaRepository, times(1)).save(Mockito.any());
  }

  @Test
  void notFoundUser() {
    when(userEntityJpaRepository.findById(anyString())).thenReturn(Optional.empty());
    recordRepository.save(record);
    verify(userEntityJpaRepository, times(1)).findById(record.getUserId().getId());
    verify(recordEntityJpaRepository, times(0)).save(Mockito.any());
  }
}
