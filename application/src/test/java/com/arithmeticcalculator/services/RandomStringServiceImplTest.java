package com.arithmeticcalculator.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.ports.in.RandomStringService;
import com.arithmeticcalculator.ports.out.RandomStringInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RandomStringServiceImplTest {
  private RandomStringInterface randomString = mock(RandomStringInterface.class);
  private RandomStringService randomStringService;

  @BeforeEach
  void init() {
    randomString = mock(RandomStringInterface.class);
    randomStringService = RandomStringServiceImpl.newInstance(randomString);
  }

  @Test
  void execute() {
    when(randomString.random()).thenReturn("random");
    var result = randomStringService.execute();
    assertEquals("random", result);
    verify(randomString, times(1)).random();
  }

  @Test
  void getOperationType() {
    assertEquals(OperationTypes.RANDOM_STRING, randomStringService.getOperationType());
  }

  @Test
  void throwException() {
    when(randomString.random()).thenThrow(IllegalStateException.class);
    assertThrows(IllegalStateException.class, randomStringService::execute);
    verify(randomString, times(1)).random();
  }

  @Test
  void newInstance() {
    assertNotSame(randomStringService, RandomStringServiceImpl.newInstance(randomString));
  }
}
