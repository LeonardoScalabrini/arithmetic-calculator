package com.arithmeticcalculator.services;

import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.commands.OperationCommand;
import com.arithmeticcalculator.domains.exceptions.IllegalStateExceptionFactory;
import com.arithmeticcalculator.domains.specifications.PaymentSpecification;
import com.arithmeticcalculator.ports.in.CreateRecordService;
import com.arithmeticcalculator.ports.in.PayCostOperationService;
import com.arithmeticcalculator.ports.out.CostOperationRepositoryInterface;
import com.arithmeticcalculator.ports.out.UserRepositoryInterface;
import lombok.NonNull;
import lombok.Value;

@Value
public class PayCostOperationServiceImpl implements PayCostOperationService {
  UserRepositoryInterface userRepository;
  CostOperationRepositoryInterface costOperationRepository;
  CreateRecordService createRecordService;
  PaymentSpecification paymentSpecification;

  private PayCostOperationServiceImpl(
      @NonNull UserRepositoryInterface userRepository,
      @NonNull CostOperationRepositoryInterface costOperationRepository,
      @NonNull CreateRecordService createRecordService,
      @NonNull PaymentSpecification paymentSpecification) {
    this.userRepository = userRepository;
    this.costOperationRepository = costOperationRepository;
    this.createRecordService = createRecordService;
    this.paymentSpecification = paymentSpecification;
  }

  @Override
  public <T> Record<T> payOperation(String email, OperationCommand<T> command) {
    var user =
        userRepository
            .findByEmail(email)
            .orElseThrow(
                () ->
                    IllegalStateExceptionFactory.builder(getClass())
                        .param("email", email)
                        .param("command", command)
                        .message("Not found user!")
                        .build());
    var costOperation =
        costOperationRepository
            .findByName(command.getOperationType())
            .orElseThrow(
                () ->
                    IllegalStateExceptionFactory.builder(getClass())
                        .param("email", email)
                        .param("command", command)
                        .message("Not found costOperation!")
                        .build());
    if (!paymentSpecification.test(user, costOperation))
      throw IllegalStateExceptionFactory.builder(getClass())
          .param("user", user)
          .param("costOperation", costOperation)
          .message("The balance is not enough for the cost operation")
          .build();
    var payedUser = user.pay(costOperation.getCost());
    var result = command.execute();
    userRepository.save(payedUser);
    return createRecordService.create(payedUser, costOperation, result);
  }

  public static PayCostOperationServiceImpl newInstance(
      @NonNull UserRepositoryInterface userRepository,
      @NonNull CostOperationRepositoryInterface costOperationRepository,
      @NonNull CreateRecordService createRecordService,
      @NonNull PaymentSpecification paymentSpecification) {
    return new PayCostOperationServiceImpl(
        userRepository, costOperationRepository, createRecordService, paymentSpecification);
  }
}
