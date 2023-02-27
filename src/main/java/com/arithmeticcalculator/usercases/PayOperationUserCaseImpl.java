package com.arithmeticcalculator.usercases;

import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.exceptions.OperationException;
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
      UserRepository userRepository,
      OperationRepository operationRepository,
      CreateRecordUserCase createRecordUserCase) {
    this.userRepository = userRepository;
    this.operationRepository = operationRepository;
    this.createRecordUserCase = createRecordUserCase;
  }

  @Override
  @Transactional
  public <T> Record<T> payOperation(@NonNull String email, @NonNull OperationCommand<T> command)
      throws OperationException {
    var user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> OperationException.withMessage("Not found user!"));
    var operation =
        operationRepository
            .findByName(command.getOperationType())
            .orElseThrow(() -> OperationException.withMessage("Not found operation!"));
    var result = command.execute();
    var payedUser = user.pay(operation);
    userRepository.save(payedUser);
    return createRecordUserCase.create(payedUser, operation, result);
  }
}
