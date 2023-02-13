package com.arithmeticcalculator.usercases;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.domains.Operation;
import com.arithmeticcalculator.domains.Operations;
import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.domains.interfaces.CreateRecordUserCase;
import com.arithmeticcalculator.domains.interfaces.OperationCommand;
import com.arithmeticcalculator.domains.interfaces.OperationRepository;
import com.arithmeticcalculator.domains.interfaces.UserRepository;
import com.arithmeticcalculator.fixtures.Fixture;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class PayOperationUserCaseImplTest {

  @Mock private UserRepository userRepository;
  @Mock private OperationRepository operationRepository;
  @Mock private CreateRecordUserCase createRecordUserCase;
  @Mock private OperationCommand<Double> operationCommand;
  @InjectMocks private PayOperationUserCaseImpl payOperationUserCase;

  @Spy private final User user = Fixture.getUser();
  private final Operation operation = Fixture.getOperation();

  @BeforeEach
  void setUp() throws OperationException {
    Mockito.when(userRepository.findByEmail(ArgumentMatchers.anyString()))
        .thenReturn(Optional.of(user));
    Mockito.doNothing().when(userRepository).save(user);
    Mockito.when(operationRepository.findByName(Operations.SQUARE_ROOT))
        .thenReturn(Optional.of(operation));
    Mockito.when(operationCommand.execute()).thenReturn(2.0);
    Mockito.doNothing().when(createRecordUserCase).create(user, operation, 2.0);
  }

  @Test
  void payOperation() throws OperationException {
    var result =
        payOperationUserCase.payOperation("email", Operations.SQUARE_ROOT, operationCommand);
    assertEquals(2, result);
    Mockito.verify(userRepository, Mockito.times(1)).findByEmail("email");
    Mockito.verify(operationRepository, Mockito.times(1)).findByName(Operations.SQUARE_ROOT);
    Mockito.verify(userRepository, Mockito.times(1)).save(user);
    Mockito.verify(user, Mockito.times(1)).pay(operation);
    Mockito.verify(operationCommand, Mockito.times(1)).execute();
    Mockito.verify(createRecordUserCase, Mockito.times(1)).create(user, operation, 2.0);
  }

  @Test
  void notFoundUser() throws OperationException {
    Mockito.when(userRepository.findByEmail(ArgumentMatchers.anyString()))
        .thenReturn(Optional.empty());
    assertThrows(
        OperationException.class,
        () -> payOperationUserCase.payOperation("email", Operations.SQUARE_ROOT, operationCommand));
    Mockito.verify(userRepository, Mockito.times(1)).findByEmail("email");
    Mockito.verify(operationRepository, Mockito.times(0)).findByName(Mockito.any());
    Mockito.verify(userRepository, Mockito.times(0)).save(Mockito.any());
    Mockito.verify(user, Mockito.times(0)).pay(Mockito.any());
    Mockito.verify(operationCommand, Mockito.times(0)).execute();
    Mockito.verify(createRecordUserCase, Mockito.times(0))
        .create(Mockito.any(), Mockito.any(), Mockito.any());
  }

  @Test
  void notFoundOperation() throws OperationException {
    Mockito.when(operationRepository.findByName(Operations.SQUARE_ROOT))
        .thenReturn(Optional.empty());
    assertThrows(
        OperationException.class,
        () -> payOperationUserCase.payOperation("email", Operations.SQUARE_ROOT, operationCommand));
    Mockito.verify(userRepository, Mockito.times(1)).findByEmail("email");
    Mockito.verify(operationRepository, Mockito.times(1)).findByName(Operations.SQUARE_ROOT);
    Mockito.verify(userRepository, Mockito.times(0)).save(Mockito.any());
    Mockito.verify(user, Mockito.times(0)).pay(Mockito.any());
    Mockito.verify(operationCommand, Mockito.times(0)).execute();
    Mockito.verify(createRecordUserCase, Mockito.times(0))
        .create(Mockito.any(), Mockito.any(), Mockito.any());
  }
}
