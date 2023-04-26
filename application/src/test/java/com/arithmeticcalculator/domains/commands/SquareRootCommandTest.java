package com.arithmeticcalculator.domains.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.arithmeticcalculator.abstracts.UtilsTest;
import com.arithmeticcalculator.domains.OperationTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SquareRootCommandTest extends UtilsTest {

  @Test
  void squareRootCommand() {
    assertClass(SquareRootCommand.class, SquareRootCommand.newInstance(1));
  }

  @ParameterizedTest
  @CsvSource({"1,1", "9,3", "0,0", "25,5"})
  void sqrt(double n, double expected) {
    assertEquals(expected, SquareRootCommand.newInstance(n).execute());
  }

  @Test
  void getOperationType() {
    assertEquals(OperationTypes.SQUARE_ROOT, SquareRootCommand.newInstance(1).getOperationType());
  }

  @Test
  void shouldThrows() {
    assertThrows(IllegalStateException.class, () -> SquareRootCommand.newInstance(-2).execute());
  }
}
