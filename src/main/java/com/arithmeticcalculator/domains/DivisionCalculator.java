package com.arithmeticcalculator.domains;

import static java.util.Objects.isNull;

import com.arithmeticcalculator.domains.exceptions.CalculatorException;
import com.arithmeticcalculator.domains.interfaces.Calculator;

public final class DivisionCalculator implements Calculator {

  private static DivisionCalculator instance;

  private DivisionCalculator() {}

  @Override
  public double calc(double n1, double n2) throws CalculatorException {
    if (n2 == 0) throw CalculatorException.withMessage("The divisor should be great than zero!");

    return n1 / n2;
  }

  public static DivisionCalculator getInstance() {
    if (isNull(instance)) instance = new DivisionCalculator();

    return instance;
  }
}
