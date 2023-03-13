package com.arithmeticcalculator.usercases;

import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.usercases.interfaces.RandomStringUserCase;
import com.arithmeticcalculator.usercases.interfaces.services.RandomString;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class RandomStringUserCaseImpl implements RandomStringUserCase {
  private final RandomString randomString;

  @Autowired
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
