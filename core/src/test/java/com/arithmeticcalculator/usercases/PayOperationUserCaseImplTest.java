package com.arithmeticcalculator.usercases;

import static com.arithmeticcalculator.domains.OperationTypes.SQUARE_ROOT;
import static com.arithmeticcalculator.fixtures.Fixture.getOperation;
import static com.arithmeticcalculator.fixtures.Fixture.getRecord;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.arithmeticcalculator.domains.*;
import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.interfaces.OperationCommand;
import com.arithmeticcalculator.usercases.interfaces.CreateRecordUserCase;
import com.arithmeticcalculator.usercases.interfaces.repositories.OperationRepository;
import com.arithmeticcalculator.usercases.interfaces.repositories.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class PayOperationUserCaseImplTest {

  @Mock private UserRepository userRepository = mock(UserRepository.class);
  @Mock private OperationRepository operationRepository = mock(OperationRepository.class);
  @Mock private CreateRecordUserCase createRecordUserCase = mock(CreateRecordUserCase.class);;
  @Mock private OperationCommand<Double> operationCommand = mock(OperationCommand.class);
  private PayOperationUserCaseImpl payOperationUserCase;
  @Mock private User user = mock(User.class);
  @Mock private User payedUser = mock(User.class);
  private final Operation operation = getOperation();
  private final Record<Double> record = getRecord();

  @BeforeEach
  void setUp() {
    when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
    when(user.pay(operation)).thenReturn(payedUser);
    doNothing().when(userRepository).save(payedUser);
    when(operationRepository.findByName(SQUARE_ROOT)).thenReturn(Optional.of(operation));
    when(operationCommand.execute()).thenReturn(2.0);
    when(operationCommand.getOperationType()).thenReturn(SQUARE_ROOT);
    when(createRecordUserCase.create(payedUser, operation, 2.0)).thenReturn(record);
    payOperationUserCase =
        new PayOperationUserCaseImpl(userRepository, operationRepository, createRecordUserCase);
  }

  @Test
  void payOperation() {
    var result = payOperationUserCase.payOperation("email", operationCommand);
    assertEquals(record, result);
    verify(userRepository, times(1)).findByEmail("email");
    verify(operationRepository, times(1)).findByName(SQUARE_ROOT);
    verify(userRepository, times(1)).save(payedUser);
    verify(user, times(1)).pay(operation);
    verify(operationCommand, times(1)).execute();
    verify(createRecordUserCase, times(1)).create(payedUser, operation, 2.0);
  }

  @Test
  void notFoundUser() {
    when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
    assertThrows(
        IllegalStateException.class,
        () -> payOperationUserCase.payOperation("email", operationCommand));
    verify(userRepository, times(1)).findByEmail("email");
    verify(operationRepository, times(0)).findByName(any());
    verify(userRepository, times(0)).save(any());
    verify(user, times(0)).pay(any());
    verify(operationCommand, times(0)).execute();
    verify(createRecordUserCase, times(0)).create(any(), any(), any());
  }

  @Test
  void notFoundOperation() {
    when(operationRepository.findByName(SQUARE_ROOT)).thenReturn(Optional.empty());
    assertThrows(
        IllegalStateException.class,
        () -> payOperationUserCase.payOperation("email", operationCommand));
    verify(userRepository, times(1)).findByEmail("email");
    verify(operationRepository, times(1)).findByName(SQUARE_ROOT);
    verify(userRepository, times(0)).save(any());
    verify(user, times(0)).pay(any());
    verify(operationCommand, times(0)).execute();
    verify(createRecordUserCase, times(0)).create(any(), any(), any());
  }
}
