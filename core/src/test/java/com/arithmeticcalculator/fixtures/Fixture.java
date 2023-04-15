package com.arithmeticcalculator.fixtures;

import com.arithmeticcalculator.domains.*;
import com.arithmeticcalculator.domains.Record;

public class Fixture {
  public static User getUser() {
    return User.builder().email("email").balance(20).build();
  }

  public static Operation getOperation() {
    return Operation.builder().cost(5).operationTypes(OperationTypes.SQUARE_ROOT).build();
  }

  public static Record<Double> getRecord() {
    return Record.from(getUser(), getOperation(), 2.0);
  }
}
