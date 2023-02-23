package com.arithmeticcalculator.domains.interfaces;

import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.domains.exceptions.OperationException;

public interface OperationCommand<T> {

  OperationTypes getOperationType();

  T execute() throws OperationException;
}
