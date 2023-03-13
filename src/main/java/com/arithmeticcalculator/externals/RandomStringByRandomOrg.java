package com.arithmeticcalculator.externals;

import com.arithmeticcalculator.externals.interfaces.RandomOrgService;
import com.arithmeticcalculator.usercases.interfaces.services.RandomString;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class RandomStringByRandomOrg implements RandomString {

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
