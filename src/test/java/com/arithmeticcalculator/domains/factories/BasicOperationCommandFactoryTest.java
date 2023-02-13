package com.arithmeticcalculator.domains.factories;

import static com.arithmeticcalculator.domains.Operations.*;
import static com.arithmeticcalculator.domains.Operations.MULTIPLICATION;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.arithmeticcalculator.domains.exceptions.OperationException;
import org.junit.jupiter.api.Test;

class BasicOperationCommandFactoryTest {

  @Test
  void of() throws OperationException {
    assertEquals(2.0, BasicOperationCommandFactory.of(ADDTION, 1, 1).execute());
    assertEquals(0.0, BasicOperationCommandFactory.of(ADDTION, 0, 0).execute());
    assertEquals(1.0, BasicOperationCommandFactory.of(SUBTRACTION, 3, 2).execute());
    assertEquals(0.0, BasicOperationCommandFactory.of(SUBTRACTION, 0, 0).execute());
    assertEquals(-1.0, BasicOperationCommandFactory.of(SUBTRACTION, 1, 2).execute());
    assertEquals(1.0, BasicOperationCommandFactory.of(DIVISION, 1, 1).execute());
    assertEquals(0.0, BasicOperationCommandFactory.of(DIVISION, 0, 1).execute());
    var division = BasicOperationCommandFactory.of(DIVISION, 1, 0);
    assertThrows(OperationException.class, division::execute);
    assertEquals(0.5, BasicOperationCommandFactory.of(DIVISION, 2, 4).execute());
    assertEquals(1.0, BasicOperationCommandFactory.of(MULTIPLICATION, 1, 1).execute());
    assertEquals(0.25, BasicOperationCommandFactory.of(MULTIPLICATION, 0.5, 0.5).execute());
    assertEquals(0.0, BasicOperationCommandFactory.of(MULTIPLICATION, 0, 0).execute());
    assertThrows(
        OperationException.class, () -> BasicOperationCommandFactory.of(RANDOM_STRING, 0, 0));
  }
}
