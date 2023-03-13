package com.arithmeticcalculator.externals;

import static org.junit.jupiter.api.Assertions.*;

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
  void setUp() {
    Mockito.when(randomOrgService.strings()).thenReturn("string");
  }

  @Test
  void random() {
    var result = randomStringByRandomOrg.random();
    assertEquals("string", result);
    Mockito.verify(randomOrgService, Mockito.times(1)).strings();
  }

  @Test
  void throwException() {
    Mockito.when(randomOrgService.strings()).thenThrow(IllegalStateException.class);
    assertThrows(IllegalStateException.class, () -> randomStringByRandomOrg.random());
    Mockito.verify(randomOrgService, Mockito.times(1)).strings();
  }
}
