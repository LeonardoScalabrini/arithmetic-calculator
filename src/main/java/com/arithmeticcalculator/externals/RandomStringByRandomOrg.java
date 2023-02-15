package com.arithmeticcalculator.externals;

import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.externals.exceptions.RandomOrgException;
import com.arithmeticcalculator.externals.interfaces.RandomOrgService;
import com.arithmeticcalculator.usercases.interfaces.services.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RandomStringByRandomOrg implements RandomString {

  private final RandomOrgService randomOrgService;

  @Autowired
  public RandomStringByRandomOrg(RandomOrgService randomOrgService) {
    this.randomOrgService = randomOrgService;
  }

  @Override
  public String random() throws OperationException {
    try {
      return randomOrgService.strings();
    } catch (RandomOrgException e) {
      throw OperationException.withMessage("Random string not able!");
    }
  }
}
