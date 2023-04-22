package com.arithmeticcalculator.services;

import static com.arithmeticcalculator.domains.OperationTypes.SQUARE_ROOT;
import static com.arithmeticcalculator.fixtures.Fixture.getCostOperation;
import static com.arithmeticcalculator.fixtures.Fixture.getRecord;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.arithmeticcalculator.domains.CostOperation;
import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.domains.commands.OperationCommand;
import com.arithmeticcalculator.domains.specifications.PaymentSpecification;
import com.arithmeticcalculator.domains.specifications.PaymentSpecificationImpl;
import com.arithmeticcalculator.ports.in.CreateRecordService;
import com.arithmeticcalculator.ports.in.PayOperationService;
import com.arithmeticcalculator.ports.out.CostOperationRepositoryInterface;
import com.arithmeticcalculator.ports.out.UserRepositoryInterface;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PayCostOperationServiceImplTest {
  private final UserRepositoryInterface userRepository = mock(UserRepositoryInterface.class);
  private final CostOperationRepositoryInterface operationRepository =
      mock(CostOperationRepositoryInterface.class);
  private final CreateRecordService createRecordService = mock(CreateRecordService.class);
  private final OperationCommand<Double> operationCommand = mock(OperationCommand.class);
  private final PaymentSpecification paymentSpecification = mock(PaymentSpecification.class);
  private PayOperationService payOperationService;
  private final User user = mock(User.class);
  private final User payedUser = mock(User.class);
  private final CostOperation costOperation = getCostOperation();
  private final Record<Double> record = getRecord();

  @BeforeEach
  void setUp() {
    when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
    when(user.pay(costOperation.getCost())).thenReturn(payedUser);
    doNothing().when(userRepository).save(payedUser);
    when(operationRepository.findByName(SQUARE_ROOT)).thenReturn(Optional.of(costOperation));
    when(operationCommand.execute()).thenReturn(2.0);
    when(operationCommand.getOperationType()).thenReturn(SQUARE_ROOT);
    when(createRecordService.create(payedUser, costOperation, 2.0)).thenReturn(record);
    when(paymentSpecification.test(user, costOperation)).thenReturn(Boolean.TRUE);
    payOperationService =
        PayCostOperationServiceImpl.newInstance(
            userRepository, operationRepository, createRecordService, paymentSpecification);
  }

  @Test
  void payOperation() {
    var result = payOperationService.payOperation("email", operationCommand);
    assertEquals(record, result);
    verify(userRepository, times(1)).findByEmail("email");
    verify(operationRepository, times(1)).findByName(SQUARE_ROOT);
    verify(paymentSpecification, times(1)).test(user, costOperation);
    verify(userRepository, times(1)).save(payedUser);
    verify(user, times(1)).pay(costOperation.getCost());
    verify(operationCommand, times(1)).execute();
    verify(createRecordService, times(1)).create(payedUser, costOperation, 2.0);
  }

  @Test
  void notPayOperation() {
    when(paymentSpecification.test(user, costOperation)).thenReturn(Boolean.FALSE);
    assertThrows(
        IllegalStateException.class,
        () -> payOperationService.payOperation("email", operationCommand));
    verify(userRepository, times(1)).findByEmail("email");
    verify(operationRepository, times(1)).findByName(SQUARE_ROOT);
    verify(paymentSpecification, times(1)).test(user, costOperation);
    verify(userRepository, times(0)).save(payedUser);
    verify(user, times(0)).pay(costOperation.getCost());
    verify(operationCommand, times(0)).execute();
    verify(createRecordService, times(0)).create(payedUser, costOperation, 2.0);
  }

  @Test
  void notFoundUser() {
    when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
    assertThrows(
        IllegalStateException.class,
        () -> payOperationService.payOperation("email", operationCommand));
    verify(userRepository, times(1)).findByEmail("email");
    verify(operationRepository, times(0)).findByName(any());
    verify(userRepository, times(0)).save(any());
    verify(user, times(0)).pay(anyDouble());
    verify(operationCommand, times(0)).execute();
    verify(createRecordService, times(0)).create(any(), any(), any());
  }

  @Test
  void notFoundOperation() {
    when(operationRepository.findByName(SQUARE_ROOT)).thenReturn(Optional.empty());
    assertThrows(
        IllegalStateException.class,
        () -> payOperationService.payOperation("email", operationCommand));
    verify(userRepository, times(1)).findByEmail("email");
    verify(operationRepository, times(1)).findByName(SQUARE_ROOT);
    verify(userRepository, times(0)).save(any());
    verify(user, times(0)).pay(anyDouble());
    verify(operationCommand, times(0)).execute();
    verify(createRecordService, times(0)).create(any(), any(), any());
  }

  @Test
  void newInstance() {
    assertNotSame(
        payOperationService,
        PayCostOperationServiceImpl.newInstance(
            userRepository,
            operationRepository,
            createRecordService,
            PaymentSpecificationImpl.getInstance()));
  }
}
