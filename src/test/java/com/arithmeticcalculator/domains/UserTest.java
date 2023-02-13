package com.arithmeticcalculator.domains;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.domains.exceptions.OperationException;
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
  void pay() throws OperationException {
    var user = Fixture.getUser();
    var operation = Fixture.getOperation();
    assertEquals(15, user.pay(operation));
    assertEquals(10, user.pay(operation));
    assertEquals(5, user.pay(operation));
    assertEquals(0, user.pay(operation));
    assertThrows(OperationException.class, () -> user.pay(operation));
  }

  @Test
  void notNull() {
    assertThrows(NullPointerException.class, () -> User.builder().build());
  }
}
