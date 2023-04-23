package com.arithmeticcalculator.interfaces.randomString;

import com.arithmeticcalculator.interfaces.randomString.randomOrg.RandomOrgService;
import com.arithmeticcalculator.ports.out.RandomStringInterface;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class RandomStringImpl implements RandomStringInterface {

  private final RandomOrgService randomOrgService;

  @Autowired
  public RandomStringImpl(@NonNull RandomOrgService randomOrgService) {
    this.randomOrgService = randomOrgService;
  }

  @Override
  public String random() {
    return randomOrgService.strings();
  }
}
