package com.arithmeticcalculator.usercases.interfaces;

import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.interfaces.OperationCommand;

public interface PayOperationUserCase {

  <T> Record<T> payOperation(String email, OperationCommand<T> command);
}
