package com.arithmeticcalculator.domains.commands;

import com.arithmeticcalculator.domains.OperationTypes;

public interface OperationCommand<T> {

  OperationTypes getOperationType();

  T execute();
}
