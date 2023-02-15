package com.arithmeticcalculator.usercases.interfaces;

import com.arithmeticcalculator.domains.Operations;
import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.domains.interfaces.OperationCommand;

public interface PayOperationUserCase {

  <T> Record<T> payOperation(String email, Operations operations, OperationCommand<T> command)
      throws OperationException;
}
