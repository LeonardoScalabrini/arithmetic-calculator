package com.arithmeticcalculator.usercases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.usercases.interfaces.services.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

class RandomStringUserCaseImplTest {

  @Mock private RandomString randomString = Mockito.mock(RandomString.class);
  private RandomStringUserCaseImpl randomStringUserCase;

  @BeforeEach
  void setUp() {
    when(randomString.random()).thenReturn("random");
    randomStringUserCase = new RandomStringUserCaseImpl(randomString);
  }

  @Test
  void execute() {
    var result = randomStringUserCase.execute();
    assertEquals("random", result);
    verify(randomString, times(1)).random();
  }

  @Test
  void getOperationType() {
    assertEquals(OperationTypes.RANDOM_STRING, randomStringUserCase.getOperationType());
  }

  @Test
  void throwException() {
    when(randomString.random()).thenThrow(IllegalStateException.class);
    assertThrows(IllegalStateException.class, () -> randomStringUserCase.execute());
    verify(randomString, times(1)).random();
  }
}
