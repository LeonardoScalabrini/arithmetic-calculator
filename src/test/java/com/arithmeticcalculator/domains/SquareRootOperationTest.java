package com.arithmeticcalculator.domains;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.domains.exceptions.CalculatorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SquareRootOperationTest {

  private SquareRootOperation squareRootOperation = SquareRootOperation.getInstance();

  @ParameterizedTest
  @CsvSource({"1,1", "9,3", "0,0", "25,5"})
  void sqrt(double n, double expected) throws CalculatorException {
    assertEquals(expected, squareRootOperation.sqrt(n));
  }

  @Test
  void shouldThrows() {
    assertThrows(CalculatorException.class, () -> squareRootOperation.sqrt(-2));
  }

  @Test
  void getInstance() {
    SquareRootOperation instance = SquareRootOperation.getInstance();
    assertEquals(instance, squareRootOperation);
  }
}
