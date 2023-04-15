package com.arithmeticcalculator.usercases;

import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.usercases.interfaces.RandomStringUserCase;
import com.arithmeticcalculator.usercases.interfaces.services.RandomString;
import lombok.NonNull;

public final class RandomStringUserCaseImpl implements RandomStringUserCase {
  private final RandomString randomString;

  public RandomStringUserCaseImpl(@NonNull RandomString randomString) {
    this.randomString = randomString;
  }

  @Override
  public OperationTypes getOperationType() {
    return OperationTypes.RANDOM_STRING;
  }

  @Override
  public String execute() {
    return randomString.random();
  }
}
