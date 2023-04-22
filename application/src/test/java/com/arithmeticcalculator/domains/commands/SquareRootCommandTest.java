package com.arithmeticcalculator.domains.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.arithmeticcalculator.domains.OperationTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SquareRootCommandTest {

  @ParameterizedTest
  @CsvSource({"1,1", "9,3", "0,0", "25,5"})
  void sqrt(double n, double expected) {
    assertEquals(expected, SquareRootCommand.of(n).execute());
  }

  @Test
  void getOperationType() {
    assertEquals(OperationTypes.SQUARE_ROOT, SquareRootCommand.of(1).getOperationType());
  }

  @Test
  void shouldThrows() {
    assertThrows(IllegalStateException.class, () -> SquareRootCommand.of(-2).execute());
  }
}
