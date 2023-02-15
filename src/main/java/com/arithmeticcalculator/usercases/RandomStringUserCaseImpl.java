package com.arithmeticcalculator.usercases;

import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.usercases.interfaces.RandomStringUserCase;
import com.arithmeticcalculator.usercases.interfaces.services.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RandomStringUserCaseImpl implements RandomStringUserCase {
  private final RandomString randomString;

  @Autowired
  public RandomStringUserCaseImpl(RandomString randomString) {
    this.randomString = randomString;
  }

  @Override
  public String execute() throws OperationException {
    return randomString.random();
  }
}
