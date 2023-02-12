package com.arithmeticcalculator.domains;

import static com.arithmeticcalculator.domains.BasicOperation.*;
import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.domains.exceptions.CalculatorException;
import org.junit.jupiter.api.Test;

class BasicOperationTest {

  @Test
  void calc() throws CalculatorException {
    assertEquals(2.0, ADDTION.calc(1, 1));
    assertEquals(0.0, ADDTION.calc(0, 0));
    assertEquals(1.0, SUBTRACTION.calc(3, 2));
    assertEquals(0.0, SUBTRACTION.calc(0, 0));
    assertEquals(-1.0, SUBTRACTION.calc(1, 2));
    assertEquals(1.0, DIVISION.calc(1, 1));
    assertEquals(0.0, DIVISION.calc(0, 1));
    assertThrows(CalculatorException.class, () -> DIVISION.calc(1, 0));
    assertEquals(0.5, DIVISION.calc(2, 4));
    assertEquals(1.0, MULTIPLICATION.calc(1, 1));
    assertEquals(0.25, MULTIPLICATION.calc(0.5, 0.5));
    assertEquals(0.0, MULTIPLICATION.calc(0, 0));
  }
}
