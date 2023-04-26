package com.arithmeticcalculator.services;

import static com.arithmeticcalculator.domains.OperationTypes.SQUARE_ROOT;
import static com.arithmeticcalculator.fixtures.Fixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;
import static org.mockito.Mockito.*;

import com.arithmeticcalculator.abstracts.UtilsTest;
import com.arithmeticcalculator.domains.CostOperation;
import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.domains.commands.OperationCommand;
import com.arithmeticcalculator.domains.specifications.PaymentSpecification;
import com.arithmeticcalculator.domains.specifications.PaymentSpecificationImpl;
import com.arithmeticcalculator.ports.in.CreateRecordService;
import com.arithmeticcalculator.ports.in.PayCostOperationService;
import com.arithmeticcalculator.ports.out.CostOperationRepositoryInterface;
import com.arithmeticcalculator.ports.out.UserRepositoryInterface;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PayCostOperationServiceImplTest extends UtilsTest {
  private final UserRepositoryInterface userRepository = mock(UserRepositoryInterface.class);
  private final CostOperationRepositoryInterface costOperationRepository =
      mock(CostOperationRepositoryInterface.class);
  private final PaymentSpecification paymentSpecification = mock(PaymentSpecification.class);
  private final CreateRecordService createRecordService = mock(CreateRecordService.class);
  private PayCostOperationService payCostOperationService;

  @BeforeEach
  void setUp() {
    payCostOperationService =
        PayCostOperationServiceImpl.newInstance(
            userRepository, costOperationRepository, createRecordService, paymentSpecification);
  }

  @Test
  void createUserServiceImpl() {
    assertClass(
        PayCostOperationServiceImpl.class,
        PayCostOperationServiceImpl.newInstance(
            userRepository, costOperationRepository, createRecordService, paymentSpecification));
  }

  @ParameterizedTest
  @MethodSource("providePayOperation")
  void payOperation(
      User user,
      CostOperation costOperation,
      boolean paySpecification,
      OperationCommand<Double> operationCommand,
      boolean isException,
      int findByName,
      int test,
      int payOperation) {
    var record = getRecord();
    when(operationCommand.getOperationType()).thenReturn(SQUARE_ROOT);
    when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(user));
    when(costOperationRepository.findByName(SQUARE_ROOT))
        .thenReturn(Optional.ofNullable(costOperation));
    when(paymentSpecification.test(user, costOperation)).thenReturn(paySpecification);
    if (user != null && costOperation != null)
      when(user.pay(costOperation.getCost())).thenReturn(user);
    when(operationCommand.execute()).thenReturn(2.0);
    doNothing().when(userRepository).save(user);
    when(createRecordService.create(user, costOperation, 2.0)).thenReturn(record);

    if (isException)
      assertThrows(
          IllegalStateException.class,
          () -> payCostOperationService.payOperation("email", operationCommand));
    else assertEquals(record, payCostOperationService.payOperation("email", operationCommand));

    verify(userRepository, times(1)).findByEmail("email");
    verify(operationCommand, times(findByName)).getOperationType();
    verify(costOperationRepository, times(findByName)).findByName(SQUARE_ROOT);
    verify(paymentSpecification, times(test)).test(user, costOperation);
    verify(userRepository, times(payOperation)).save(user);
    if (user != null && costOperation != null)
      verify(user, times(payOperation)).pay(costOperation.getCost());
    verify(operationCommand, times(payOperation)).execute();
    verify(createRecordService, times(payOperation)).create(user, costOperation, 2.0);
  }

  @Test
  void newInstance() {
    assertNotSame(
        payCostOperationService,
        PayCostOperationServiceImpl.newInstance(
            userRepository,
            costOperationRepository,
            createRecordService,
            PaymentSpecificationImpl.getInstance()));
  }

  private static Stream<Arguments> providePayOperation() {
    return Stream.of(
        of(
            mock(User.class),
            mock(CostOperation.class),
            true,
            mock(OperationCommand.class),
            false,
            1,
            1,
            1),
        of(mock(User.class), null, true, mock(OperationCommand.class), true, 1, 0, 0),
        of(
            mock(User.class),
            mock(CostOperation.class),
            false,
            mock(OperationCommand.class),
            true,
            1,
            1,
            0),
        of(mock(User.class), null, false, mock(OperationCommand.class), true, 1, 0, 0),
        of(null, mock(CostOperation.class), true, mock(OperationCommand.class), true, 0, 0, 0),
        of(null, null, true, mock(OperationCommand.class), true, 0, 0, 0),
        of(null, mock(CostOperation.class), false, mock(OperationCommand.class), true, 0, 0, 0),
        of(null, null, false, mock(OperationCommand.class), true, 0, 0, 0));
  }
}
