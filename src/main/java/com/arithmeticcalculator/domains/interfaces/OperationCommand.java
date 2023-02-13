package com.arithmeticcalculator.domains.interfaces;

import com.arithmeticcalculator.domains.exceptions.OperationException;

public interface OperationCommand<T> {
  T execute() throws OperationException;
}
