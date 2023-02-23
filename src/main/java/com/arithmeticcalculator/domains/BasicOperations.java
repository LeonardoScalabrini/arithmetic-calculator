package com.arithmeticcalculator.domains;

import static com.arithmeticcalculator.domains.OperationTypes.*;

import com.arithmeticcalculator.domains.commands.BasicOperationCommand;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.domains.interfaces.Calculator;
import com.arithmeticcalculator.domains.interfaces.OperationCommand;

public enum BasicOperations implements Calculator {
  PLUS(ADDITION) {
    public double calc(double n1, double n2) {
      return Double.sum(n1, n2);
    }
  },
  MINUS(SUBTRACTION) {
    public double calc(double n1, double n2) {
      return n1 - n2;
    }
  },
  TIMES(MULTIPLICATION) {
    public double calc(double n1, double n2) {
      return n1 * n2;
    }
  },
  DIVIDE(DIVISION) {
    public double calc(double dividend, double divisor) throws OperationException {
      if (divisor == 0)
        throw OperationException.withMessage("The divisor should be great than zero!");
      return dividend / divisor;
    }
  };

  private final OperationTypes operationType;

  BasicOperations(OperationTypes operationType) {
    this.operationType = operationType;
  }

  public OperationCommand<Double> getOperationCommand(double n1, double n2) {
    return BasicOperationCommand.of(this, n1, n2);
  }

  public OperationTypes getOperationType() {
    return operationType;
  }
}
