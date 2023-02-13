package com.arithmeticcalculator.domains.commands;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.domains.exceptions.OperationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DivisionCommandTest {

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
  void calc(double n1, double n2, double expected) throws OperationException {
    assertEquals(expected, DivisionCommand.of(n1, n2).execute());
  }

  @Test
  void shouldThrows() {
    assertThrows(OperationException.class, () -> DivisionCommand.of(1, 0).execute());
  }
}
