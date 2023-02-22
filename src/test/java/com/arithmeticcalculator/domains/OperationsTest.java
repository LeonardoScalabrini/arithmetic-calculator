package com.arithmeticcalculator.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OperationsTest {
  @ParameterizedTest
  @CsvSource({
    "ADDTION,true",
    "SUBTRACTION,true",
    "MULTIPLICATION,true",
    "DIVISION,true",
    "SQUARE_ROOT,true",
    "RANDOM_STRING,true",
    "RANDOMSTRING,false",
    "NotExist,false",
    "addtion,true",
    "Addtion,true"
  })
  void from(String name, boolean expected) {
    assertEquals(expected, Operations.from(name).isPresent());
  }

  @ParameterizedTest
  @CsvSource({
    "ADDTION,ADDTION",
    "SUBTRACTION,SUBTRACTION",
    "MULTIPLICATION,MULTIPLICATION",
    "DIVISION,DIVISION",
    "SQUARE_ROOT,SQUARE_ROOT",
    "RANDOM_STRING,RANDOM_STRING"
  })
  void getName(Operations operations, String expected) {
    assertEquals(expected, operations.getName());
  }
}
