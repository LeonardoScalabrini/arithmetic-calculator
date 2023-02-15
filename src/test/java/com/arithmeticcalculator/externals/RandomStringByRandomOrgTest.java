package com.arithmeticcalculator.externals;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.externals.exceptions.RandomOrgException;
import com.arithmeticcalculator.externals.interfaces.RandomOrgService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RandomStringByRandomOrgTest {

  @Mock private RandomOrgService randomOrgService;
  @InjectMocks private RandomStringByRandomOrg randomStringByRandomOrg;

  @BeforeEach
  void setUp() throws RandomOrgException {
    Mockito.when(randomOrgService.strings()).thenReturn("string");
  }

  @Test
  void random() throws OperationException, RandomOrgException {
    var result = randomStringByRandomOrg.random();
    assertEquals("string", result);
    Mockito.verify(randomOrgService, Mockito.times(1)).strings();
  }

  @Test
  void throwException() throws RandomOrgException {
    Mockito.when(randomOrgService.strings()).thenThrow(RandomOrgException.class);
    assertThrows(OperationException.class, () -> randomStringByRandomOrg.random());
    Mockito.verify(randomOrgService, Mockito.times(1)).strings();
  }
}
