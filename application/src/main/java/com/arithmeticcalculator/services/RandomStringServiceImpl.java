package com.arithmeticcalculator.services;

import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.ports.in.RandomStringService;
import com.arithmeticcalculator.ports.out.RandomStringInterface;
import lombok.NonNull;

public final class RandomStringServiceImpl implements RandomStringService {
  private final RandomStringInterface randomString;

  private RandomStringServiceImpl(@NonNull RandomStringInterface randomString) {
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

  public static RandomStringService newInstance(@NonNull RandomStringInterface randomString) {
    return new RandomStringServiceImpl(randomString);
  }
}
