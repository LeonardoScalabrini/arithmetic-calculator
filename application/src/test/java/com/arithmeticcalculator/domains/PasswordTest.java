package com.arithmeticcalculator.domains;

import static com.arithmeticcalculator.fixtures.Fixture.CRYPTED_STRING;
import static com.arithmeticcalculator.fixtures.Fixture.getPassword;
import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.abstracts.UtilsTest;
import org.junit.jupiter.api.Test;

class PasswordTest extends UtilsTest {

  @Test
  void password() {
    assertClass(Password.class, getPassword());
    var p = getPassword();
    var cryptPassword = getPassword(p.getCryptedPassword());
    var password = getPassword();
    assertNotEquals(p, password);
    assertEquals(p, cryptPassword);
    assertEquals(CRYPTED_STRING, getPassword(CRYPTED_STRING).getCryptedPassword());
  }
}
