package com.arithmeticcalculator.services;

import static com.arithmeticcalculator.fixtures.Fixture.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.mockito.Mockito.*;

import com.arithmeticcalculator.abstracts.UtilsTest;
import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.ports.in.CreateRecordService;
import com.arithmeticcalculator.ports.out.RecordRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateRecordServiceImplTest extends UtilsTest {
  private final RecordRepositoryInterface recordRepository = mock(RecordRepositoryInterface.class);
  private CreateRecordService createRecordService;
  private final Record<Double> record = getRecord();

  @BeforeEach
  void setUp() {
    doNothing().when(recordRepository).save(record);
    createRecordService = CreateRecordServiceImpl.newInstance(recordRepository);
  }

  @Test
  void createRecordServiceImpl() {
    assertClass(
        CreateRecordServiceImpl.class, CreateRecordServiceImpl.newInstance(recordRepository));
  }

  @Test
  void create() {
    createRecordService.create(getUser(), getCostOperation(), 2.0);
    verify(recordRepository, times(1)).save(any());
  }

  @Test
  void newInstance() {
    assertNotSame(createRecordService, CreateRecordServiceImpl.newInstance(recordRepository));
  }
}
