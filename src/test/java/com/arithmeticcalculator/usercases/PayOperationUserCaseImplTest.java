package com.arithmeticcalculator.usercases;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.domains.*;
import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.domains.interfaces.OperationCommand;
import com.arithmeticcalculator.fixtures.Fixture;
import com.arithmeticcalculator.usercases.interfaces.CreateRecordUserCase;
import com.arithmeticcalculator.usercases.interfaces.repositories.OperationRepository;
import com.arithmeticcalculator.usercases.interfaces.repositories.UserRepository;
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

  private final Record<Double> record = Fixture.getRecord();

  @BeforeEach
  void setUp() throws OperationException {
    Mockito.when(userRepository.findByEmail(ArgumentMatchers.anyString()))
        .thenReturn(Optional.of(user));
    Mockito.doNothing().when(userRepository).save(user);
    Mockito.when(operationRepository.findByName(OperationTypes.SQUARE_ROOT))
        .thenReturn(Optional.of(operation));
    Mockito.when(operationCommand.execute()).thenReturn(2.0);
    Mockito.when(operationCommand.getOperationType()).thenReturn(OperationTypes.SQUARE_ROOT);
    Mockito.when(createRecordUserCase.create(user, operation, 2.0)).thenReturn(record);
  }

  @Test
  void payOperation() throws OperationException {
    var result = payOperationUserCase.payOperation("email", operationCommand);
    assertEquals(record, result);
    Mockito.verify(userRepository, Mockito.times(1)).findByEmail("email");
    Mockito.verify(operationRepository, Mockito.times(1)).findByName(OperationTypes.SQUARE_ROOT);
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
        () -> payOperationUserCase.payOperation("email", operationCommand));
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
    Mockito.when(operationRepository.findByName(OperationTypes.SQUARE_ROOT))
        .thenReturn(Optional.empty());
    assertThrows(
        OperationException.class,
        () -> payOperationUserCase.payOperation("email", operationCommand));
    Mockito.verify(userRepository, Mockito.times(1)).findByEmail("email");
    Mockito.verify(operationRepository, Mockito.times(1)).findByName(OperationTypes.SQUARE_ROOT);
    Mockito.verify(userRepository, Mockito.times(0)).save(Mockito.any());
    Mockito.verify(user, Mockito.times(0)).pay(Mockito.any());
    Mockito.verify(operationCommand, Mockito.times(0)).execute();
    Mockito.verify(createRecordUserCase, Mockito.times(0))
        .create(Mockito.any(), Mockito.any(), Mockito.any());
  }
}
