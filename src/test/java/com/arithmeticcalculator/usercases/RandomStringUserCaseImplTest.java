package com.arithmeticcalculator.usercases;

import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.usercases.interfaces.services.RandomString;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RandomStringUserCaseImplTest {

  @Mock private RandomString randomString;
  @InjectMocks private RandomStringUserCaseImpl randomStringUserCase;

  @BeforeEach
  void setUp() throws OperationException {
    Mockito.when(randomString.random()).thenReturn("random");
  }

  @Test
  void execute() throws OperationException {
    var result = randomStringUserCase.execute();
    Assertions.assertEquals("random", result);
    Mockito.verify(randomString, Mockito.times(1)).random();
  }

  @Test
  void throwException() throws OperationException {
    Mockito.when(randomString.random()).thenThrow(OperationException.class);
    Assert.assertThrows(OperationException.class, () -> randomStringUserCase.execute());
    Mockito.verify(randomString, Mockito.times(1)).random();
  }
}
