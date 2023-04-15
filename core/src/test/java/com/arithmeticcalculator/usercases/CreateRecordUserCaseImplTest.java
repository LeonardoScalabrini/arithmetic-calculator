package com.arithmeticcalculator.usercases;

import static org.mockito.Mockito.*;

import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.fixtures.Fixture;
import com.arithmeticcalculator.usercases.interfaces.repositories.RecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

class CreateRecordUserCaseImplTest {

  @Mock private RecordRepository recordRepository = mock(RecordRepository.class);
  private CreateRecordUserCaseImpl createRecordUserCase;
  private final Record<Double> record = Fixture.getRecord();

  @BeforeEach
  void setUp() {
    doNothing().when(recordRepository).save(record);
    createRecordUserCase = new CreateRecordUserCaseImpl(recordRepository);
  }

  @Test
  void create() {
    createRecordUserCase.create(Fixture.getUser(), Fixture.getOperation(), 2.0);
    verify(recordRepository, Mockito.times(1)).save(Mockito.any());
  }
}
