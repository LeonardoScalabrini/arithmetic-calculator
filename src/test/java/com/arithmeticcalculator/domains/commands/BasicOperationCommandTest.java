package com.arithmeticcalculator.domains.commands;

import static com.arithmeticcalculator.domains.BasicOperations.*;
import static com.arithmeticcalculator.domains.BasicOperations.TIMES;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.arithmeticcalculator.domains.BasicOperations;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BasicOperationCommandTest {

  @Test
  void of() throws OperationException {
    assertEquals(2.0, BasicOperationCommand.of(PLUS, 1, 1).execute());
    assertEquals(0.0, BasicOperationCommand.of(PLUS, 0, 0).execute());
    assertEquals(1.0, BasicOperationCommand.of(MINUS, 3, 2).execute());
    assertEquals(0.0, BasicOperationCommand.of(MINUS, 0, 0).execute());
    assertEquals(-1.0, BasicOperationCommand.of(MINUS, 1, 2).execute());
    assertEquals(1.0, BasicOperationCommand.of(DIVIDE, 1, 1).execute());
    assertEquals(0.0, BasicOperationCommand.of(DIVIDE, 0, 1).execute());
    assertEquals(-2.0, BasicOperationCommand.of(DIVIDE, 2.0, -1.0).execute());
    assertEquals(-0.5, BasicOperationCommand.of(DIVIDE, 1.0, -2.0).execute());
    assertEquals(-0.5, BasicOperationCommand.of(DIVIDE, -1.0, 2.0).execute());
    var division = BasicOperationCommand.of(DIVIDE, 1, 0);
    assertThrows(OperationException.class, division::execute);
    assertEquals(0.5, BasicOperationCommand.of(DIVIDE, 2, 4).execute());
    assertEquals(1.0, BasicOperationCommand.of(TIMES, 1, 1).execute());
    assertEquals(0.25, BasicOperationCommand.of(TIMES, 0.5, 0.5).execute());
    assertEquals(0.0, BasicOperationCommand.of(TIMES, 0, 0).execute());
  }

  @ParameterizedTest
  @CsvSource({"PLUS", "MINUS", "DIVIDE", "TIMES"})
  void getOperationType(BasicOperations basicOperation) {
    assertEquals(
        basicOperation.getOperationType(),
        BasicOperationCommand.of(basicOperation, 1, 1).getOperationType());
  }
}
