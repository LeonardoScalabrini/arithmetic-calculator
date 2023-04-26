package com.arithmeticcalculator.services;

import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.ports.in.RandomStringService;
import com.arithmeticcalculator.ports.out.RandomStringInterface;
import lombok.NonNull;
import lombok.Value;

@Value
public class RandomStringServiceImpl implements RandomStringService {
  RandomStringInterface randomString;

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

  public static RandomStringServiceImpl newInstance(@NonNull RandomStringInterface randomString) {
    return new RandomStringServiceImpl(randomString);
  }
}
