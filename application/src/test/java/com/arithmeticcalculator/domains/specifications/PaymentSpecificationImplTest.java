package com.arithmeticcalculator.domains.specifications;

import static com.arithmeticcalculator.fixtures.Fixture.getCostOperation;
import static com.arithmeticcalculator.fixtures.Fixture.getUser;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaymentSpecificationImplTest {

  @Test
  void test() {
    var paymentSpecification = PaymentSpecificationImpl.getInstance();
    assertTrue(paymentSpecification.test(getUser(), getCostOperation()));
    assertFalse(paymentSpecification.test(getUser().pay(100), getCostOperation()));
  }

  @Test
  void getInstance() {
    assertSame(PaymentSpecificationImpl.getInstance(), PaymentSpecificationImpl.getInstance());
  }
}
