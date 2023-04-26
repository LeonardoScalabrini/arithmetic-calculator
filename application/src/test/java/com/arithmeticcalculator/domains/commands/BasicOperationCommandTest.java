package com.arithmeticcalculator.domains.commands;

import static com.arithmeticcalculator.domains.commands.BasicOperations.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.of;

import com.arithmeticcalculator.abstracts.UtilsTest;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

class BasicOperationCommandTest extends UtilsTest {

  @Test
  void basicOperatiomCommand() {
    assertClass(BasicOperationCommand.class, BasicOperationCommand.newInstance(PLUS, 1, 1));
  }

  @ParameterizedTest
  @MethodSource("provideExecute")
  void execute(
      BasicOperations basicOperations,
      double n1,
      double n2,
      double expected,
      boolean shouldThrowException) {
    var command = BasicOperationCommand.newInstance(basicOperations, n1, n2);
    if (shouldThrowException) assertThrows(IllegalStateException.class, command::execute);
    else assertEquals(expected, command.execute());
  }

  @ParameterizedTest
  @EnumSource(BasicOperations.class)
  void getOperationType(BasicOperations basicOperation) {
    assertEquals(
        basicOperation.getOperationType(),
        BasicOperationCommand.newInstance(basicOperation, 1, 1).getOperationType());
  }

  private static Stream<Arguments> provideExecute() {
    return Stream.of(
        of(PLUS, 1.0, 1.0, 2.0, false),
        of(PLUS, 0.0, -1.0, -1.0, false),
        of(PLUS, 0.0, 0.0, 0.0, false),
        of(PLUS, -1.0, -1.0, -2.0, false),
        of(MINUS, 1.0, 1.0, 0.0, false),
        of(MINUS, 0.0, -1.0, 1.0, false),
        of(MINUS, 0.0, 0.0, 0.0, false),
        of(MINUS, -1.0, -1.0, 0.0, false),
        of(DIVIDE, 1.0, 1.0, 1.0, false),
        of(DIVIDE, 0.0, -1.0, -0.0, false),
        of(DIVIDE, 0.0, 0.0, 0.0, true),
        of(DIVIDE, -1.0, -1.0, 1.0, false),
        of(TIMES, 1.0, 1.0, 1.0, false),
        of(TIMES, 0.0, -1.0, -0.0, false),
        of(TIMES, 0.0, 0.0, 0.0, false),
        of(TIMES, -1.0, -1.0, 1.0, false));
  }
}
