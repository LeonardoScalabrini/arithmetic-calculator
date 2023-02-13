package com.arithmeticcalculator.usercases;

import com.arithmeticcalculator.domains.Operations;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.domains.interfaces.*;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class PayOperationUserCaseImpl implements PayOperationUserCase {

  private final UserRepository userRepository;
  private final OperationRepository operationRepository;

  private final CreateRecordUserCase createRecordUserCase;

  @Autowired
  public PayOperationUserCaseImpl(
          UserRepository userRepository, OperationRepository operationRepository, CreateRecordUserCase createRecordUserCase) {
    this.userRepository = userRepository;
    this.operationRepository = operationRepository;
    this.createRecordUserCase = createRecordUserCase;
  }

  @Override
  @Transactional
  public <T> T payOperation(
      @NonNull String email, @NonNull Operations operations, @NonNull OperationCommand<T> command)
      throws OperationException {
    var user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> OperationException.withMessage("Not found user!"));
    var operation =
        operationRepository
            .findByName(operations)
            .orElseThrow(() -> OperationException.withMessage("Not found operation!"));
    var result = command.execute();
    user.pay(operation);
    userRepository.save(user);
    createRecordUserCase.create(user, operation, result);
    return result;
  }
}
