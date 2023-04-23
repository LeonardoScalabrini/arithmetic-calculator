package com.arithmeticcalculator.interfaces.randomString;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.interfaces.randomString.randomOrg.RandomOrgService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RandomStringImplTest {

  @Mock private RandomOrgService randomOrgService;
  @InjectMocks private RandomStringImpl randomStringImpl;

  @BeforeEach
  void setUp() {
    Mockito.when(randomOrgService.strings()).thenReturn("string");
  }

  @Test
  void random() {
    var result = randomStringImpl.random();
    assertEquals("string", result);
    Mockito.verify(randomOrgService, Mockito.times(1)).strings();
  }

  @Test
  void throwException() {
    Mockito.when(randomOrgService.strings()).thenThrow(IllegalStateException.class);
    assertThrows(IllegalStateException.class, () -> randomStringImpl.random());
    Mockito.verify(randomOrgService, Mockito.times(1)).strings();
  }
}
