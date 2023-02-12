package com.arithmeticcalculator.domains;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.domains.exceptions.CalculatorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DivisionCalculatorTest {

  private final DivisionCalculator divisionCalculator = DivisionCalculator.getInstance();

  @ParameterizedTest
  @CsvSource({
    "1.0,1.0,1.0",
    "2.0,-1.0,-2.0",
    "-2.0,1.0,-2.0",
    "-2.0,-1.0,2.0",
    "0.0,1.0,0.0",
    "2.0,4.0,0.5",
    "0.5,0.25,2.0"
  })
  void calc(double n1, double n2, double expected) throws CalculatorException {
    assertEquals(expected, divisionCalculator.calc(n1, n2));
  }

  @Test
  void shouldThrows() {
    assertThrows(CalculatorException.class, () -> divisionCalculator.calc(1, 0));
  }

  @Test
  void getInstance() {
    DivisionCalculator instance = DivisionCalculator.getInstance();
    assertEquals(instance, divisionCalculator);
  }
}
