package com.arithmeticcalculator.usercases;

import com.arithmeticcalculator.domains.Operations;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.domains.interfaces.OperationCommand;
import com.arithmeticcalculator.domains.interfaces.OperationRepository;
import com.arithmeticcalculator.domains.interfaces.PayOperationUserCase;
import com.arithmeticcalculator.domains.interfaces.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PayOperationUserCaseImpl implements PayOperationUserCase {

  private final UserRepository userRepository;
  private final OperationRepository operationRepository;

  @Autowired
  public PayOperationUserCaseImpl(
      UserRepository userRepository, OperationRepository operationRepository) {
    this.userRepository = userRepository;
    this.operationRepository = operationRepository;
  }

  @Override
  public <T> T payOperation(
      @NonNull String email, @NonNull Operations operations, @NonNull OperationCommand<T> command)
      throws OperationException {
    var user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> OperationException.withMessage("Not found user"));
    var operation =
        operationRepository
            .findByName(operations)
            .orElseThrow(() -> OperationException.withMessage("Not found operation"));
    user.pay(operation);
    userRepository.save(user);
    return command.execute();
  }
}
