package com.arithmeticcalculator.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PasswordTest {

  @Test
  void newInstance() {
    assertThrows(RuntimeException.class, () -> Password.newInstance(null));
    assertDoesNotThrow(() -> Password.newInstance("password"));
  }

  @Test
  void getValue() {
    Password password = Password.newInstance("password");
    assertNotNull(password.getCryptedPassword());
    assertNotEquals("password", password.getCryptedPassword());
    assertNotEquals(
        Password.newInstance("password").getCryptedPassword(), password.getCryptedPassword());
  }

  @Test
  void check() {
    Password password = Password.newInstance("password");
    assertTrue(password.check("password"));
    assertFalse(password.check("password2"));
  }
}
