package com.arithmeticcalculator.domains.interfaces;

import com.arithmeticcalculator.domains.Operations;
import com.arithmeticcalculator.domains.exceptions.OperationException;

public interface PayOperationUserCase {

  <T> T payOperation(String email, Operations operations, OperationCommand<T> command)
      throws OperationException;
}
