package com.arithmeticcalculator.usercases;

import static com.arithmeticcalculator.domains.exceptions.IllegalStateExceptionFactory.getInstance;

import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.interfaces.*;
import com.arithmeticcalculator.usercases.interfaces.CreateRecordUserCase;
import com.arithmeticcalculator.usercases.interfaces.PayOperationUserCase;
import com.arithmeticcalculator.usercases.interfaces.repositories.OperationRepository;
import com.arithmeticcalculator.usercases.interfaces.repositories.UserRepository;
import javax.transaction.Transactional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PayOperationUserCaseImpl implements PayOperationUserCase {

  private final UserRepository userRepository;
  private final OperationRepository operationRepository;

  private final CreateRecordUserCase createRecordUserCase;

  @Autowired
  public PayOperationUserCaseImpl(
      @NonNull UserRepository userRepository,
      @NonNull OperationRepository operationRepository,
      @NonNull CreateRecordUserCase createRecordUserCase) {
    this.userRepository = userRepository;
    this.operationRepository = operationRepository;
    this.createRecordUserCase = createRecordUserCase;
  }

  @Override
  @Transactional
  public <T> Record<T> payOperation(@NonNull String email, @NonNull OperationCommand<T> command) {

    var user =
        userRepository
            .findByEmail(email)
            .orElseThrow(
                () ->
                    getInstance()
                        .param("email", email)
                        .param("command", command)
                        .message("Not found user!")
                        .build());
    var operation =
        operationRepository
            .findByName(command.getOperationType())
            .orElseThrow(
                () ->
                    getInstance()
                        .param("email", email)
                        .param("command", command)
                        .message("Not found operation!")
                        .build());
    var result = command.execute();
    var payedUser = user.pay(operation);
    userRepository.save(payedUser);
    return createRecordUserCase.create(payedUser, operation, result);
  }
}
