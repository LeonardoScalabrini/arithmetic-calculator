package com.arithmeticcalculator.usercases;

import com.arithmeticcalculator.domains.OperationTypes;
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
  void setUp() {
    Mockito.when(randomString.random()).thenReturn("random");
  }

  @Test
  void execute() {
    var result = randomStringUserCase.execute();
    Assertions.assertEquals("random", result);
    Mockito.verify(randomString, Mockito.times(1)).random();
  }

  @Test
  void getOperationType() {
    Assertions.assertEquals(OperationTypes.RANDOM_STRING, randomStringUserCase.getOperationType());
  }

  @Test
  void throwException() {
    Mockito.when(randomString.random()).thenThrow(IllegalStateException.class);
    Assert.assertThrows(IllegalStateException.class, () -> randomStringUserCase.execute());
    Mockito.verify(randomString, Mockito.times(1)).random();
  }
}
