package com.arithmeticcalculator.domains.commands;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.domains.interfaces.StringGeneratorService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class StringGeneratorCommandTest {
  @Mock private StringGeneratorService stringGeneratorService;
  @InjectMocks private StringGeneratorCommand stringGeneratorCommand;

  @BeforeEach
  void setUp() {
    Mockito.when(stringGeneratorService.stringGenerator()).thenReturn(Optional.of("random"));
  }

  @Test
  void execute() throws OperationException {
    var result = stringGeneratorCommand.execute();
    assertEquals("random", result);
    Mockito.verify(stringGeneratorService, Mockito.times(1)).stringGenerator();
  }

  @Test
  void throwError() {
    Mockito.when(stringGeneratorService.stringGenerator()).thenReturn(Optional.empty());
    assertThrows(OperationException.class, () -> stringGeneratorCommand.execute());
    Mockito.verify(stringGeneratorService, Mockito.times(1)).stringGenerator();
  }
}
