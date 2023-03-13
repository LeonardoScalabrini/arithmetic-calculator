package com.arithmeticcalculator.domains.interfaces;

import com.arithmeticcalculator.domains.OperationTypes;

public interface OperationCommand<T> {

  OperationTypes getOperationType();

  T execute();
}
