package com.arithmeticcalculator.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BasicOperationsTest {

  @ParameterizedTest
  @CsvSource({"PLUS,1.0,2.0,3.0", "MINUS,1.0,2.0,-1.0", "DIVIDE,1.0,2.0,0.5", "TIMES,1.0,2.0,2.0"})
  void applyAsDouble(BasicOperations basicOperations, double n, double n2, double expected) {
    assertEquals(expected, basicOperations.applyAsDouble(n, n2));
  }

  @ParameterizedTest
  @CsvSource({"DIVIDE,1.0,0.0"})
  void applyAsDouble(BasicOperations basicOperations, double n, double n2) {
    assertThrows(IllegalStateException.class, () -> basicOperations.applyAsDouble(n, n2));
  }

  @ParameterizedTest
  @CsvSource({"PLUS,1.0,2.0", "MINUS,1.0,2.0", "DIVIDE,1.0,2.0", "TIMES,1.0,2.0"})
  void getOperationCommand(BasicOperations basicOperations, double n, double n2) {
    assertNotNull(basicOperations.getOperationCommand(n, n2));
  }

  @ParameterizedTest
  @CsvSource({"PLUS,ADDITION", "MINUS,SUBTRACTION", "DIVIDE,DIVISION", "TIMES,MULTIPLICATION"})
  void getOperationType(BasicOperations basicOperations, OperationTypes expected) {
    assertEquals(expected, basicOperations.getOperationType());
  }
}
