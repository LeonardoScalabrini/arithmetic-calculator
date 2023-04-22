package com.arithmeticcalculator.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.arithmeticcalculator.fixtures.Fixture;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  void build() {
    var user = Fixture.getUser();
    assertEquals("email", user.getEmail());
    assertEquals(20, user.getBalance());
    EqualsVerifier.simple().forClass(User.class).suppress(Warning.STRICT_INHERITANCE).verify();
    ToStringVerifier.forClass(User.class).verify();
  }

  @Test
  void pay() {
    var user = Fixture.getUser();
    var operation = Fixture.getCostOperation();
    assertEquals(15, user.pay(operation.getCost()).getBalance());
    assertEquals(10, user.pay(operation.getCost()).pay(operation.getCost()).getBalance());
    assertEquals(
        5,
        user.pay(operation.getCost())
            .pay(operation.getCost())
            .pay(operation.getCost())
            .getBalance());
    assertEquals(
        0,
        user.pay(operation.getCost())
            .pay(operation.getCost())
            .pay(operation.getCost())
            .pay(operation.getCost())
            .getBalance());
    assertEquals(
        -5,
        user.pay(operation.getCost())
            .pay(operation.getCost())
            .pay(operation.getCost())
            .pay(operation.getCost())
            .pay(operation.getCost())
            .getBalance());
  }

  @Test
  void addBalance() {
    var user = Fixture.getUser();
    var userBalance = user.addBalance(15);
    assertEquals(35, userBalance.getBalance());
    assertEquals(user.getUserId(), userBalance.getUserId());
    assertEquals(user.getEmail(), userBalance.getEmail());
  }

  @Test
  void notNull() {
    assertThrows(NullPointerException.class, () -> User.builder().balance(5).build());
  }
}
