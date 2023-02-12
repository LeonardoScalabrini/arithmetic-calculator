package com.arithmeticcalculator.domains;

import static com.arithmeticcalculator.domains.DivisionCalculator.getInstance;

import com.arithmeticcalculator.domains.exceptions.CalculatorException;
import com.arithmeticcalculator.domains.interfaces.Calculator;

public enum BasicOperation implements Calculator {
  ADDTION(Double::sum),
  SUBTRACTION((n1, n2) -> n1 - n2),
  MULTIPLICATION((n1, n2) -> n1 * n2),
  DIVISION(getInstance());

  private final Calculator calculator;

  BasicOperation(Calculator calculator) {
    this.calculator = calculator;
  }

  @Override
  public double calc(double n1, double n2) throws CalculatorException {
    return calculator.calc(n1, n2);
  }
}
