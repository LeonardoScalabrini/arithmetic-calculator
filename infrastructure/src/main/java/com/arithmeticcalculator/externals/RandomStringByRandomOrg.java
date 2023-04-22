package com.arithmeticcalculator.externals;

import com.arithmeticcalculator.ports.out.RandomStringInterface;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class RandomStringByRandomOrg implements RandomStringInterface {

  private final RandomOrgService randomOrgService;

  @Autowired
  public RandomStringByRandomOrg(@NonNull RandomOrgService randomOrgService) {
    this.randomOrgService = randomOrgService;
  }

  @Override
  public String random() {
    return randomOrgService.strings();
  }
}
