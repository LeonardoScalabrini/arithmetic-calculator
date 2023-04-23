package com.arithmeticcalculator.domains.specifications;

import com.arithmeticcalculator.domains.CostOperation;
import com.arithmeticcalculator.domains.User;
import lombok.Value;

@Value
public class PaymentSpecificationImpl implements PaymentSpecification {
  private static PaymentSpecification instance;

  private PaymentSpecificationImpl() {}

  @Override
  public boolean test(User user, CostOperation costOperation) {
    return costOperation.getCost() <= user.getBalance();
  }

  public static PaymentSpecification getInstance() {
    if (instance == null) instance = new PaymentSpecificationImpl();
    return instance;
  }
}
