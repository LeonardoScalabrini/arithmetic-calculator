package com.arithmeticcalculator.domains.interfaces;

import com.arithmeticcalculator.domains.exceptions.OperationException;

public interface Calculator {
  double calc(double n1, double n2) throws OperationException;
}
