package com.arithmeticcalculator.usercases;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.interfaces.RecordRepository;
import com.arithmeticcalculator.fixtures.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CreateRecordUserCaseImplTest {

  @Mock private RecordRepository recordRepository;
  @InjectMocks private CreateRecordUserCaseImpl createRecordUserCase;

  private final Record<Double> record = Fixture.getRecord();

  @BeforeEach
  void setUp() {
    Mockito.doNothing().when(recordRepository).save(record);
  }

  @Test
  void create() {
    createRecordUserCase.create(Fixture.getUser(), Fixture.getOperation(), 2.0);
    Mockito.verify(recordRepository, Mockito.times(1)).save(Mockito.any());
  }
}
