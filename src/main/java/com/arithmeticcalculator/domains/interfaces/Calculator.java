package com.arithmeticcalculator.domains.interfaces;

import com.arithmeticcalculator.domains.exceptions.CalculatorException;

public interface Calculator {
  double calc(double n1, double n2) throws CalculatorException;
}
