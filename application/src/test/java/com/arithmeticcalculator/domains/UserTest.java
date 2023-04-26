package com.arithmeticcalculator.domains;

import static com.arithmeticcalculator.fixtures.Fixture.getUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.arithmeticcalculator.abstracts.UtilsTest;
import com.arithmeticcalculator.domains.ids.UserId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UserTest extends UtilsTest {

  @Test
  void user() {
    assertClass(User.class, getUser());
    var userId = UserId.newInstance();
    var password = Password.newInstance("password");
    var user = getUser(userId, password);
    assertEquals(userId, user.getUserId());
    assertEquals(password, user.getPassword());
    assertEquals(20, user.getBalance());
    assertEquals("email", user.getEmail());
    assertEquals(Privileges.USER, user.getPrivileges());
  }

  @ParameterizedTest
  @CsvSource({
    "5, false, 15",
    "10, false, 10",
    "20, false, 0",
    "25, false, -5",
    "0.5, false, 19.5",
    "-5, true, 20"
  })
  void pay(double value, boolean isException, double expected) {
    var user = getUser();
    if (isException) assertThrows(IllegalStateException.class, () -> user.pay(value));
    else assertEquals(expected, user.pay(value).getBalance());
  }

  @ParameterizedTest
  @CsvSource({"5, 25", "15, 35", "0, 20", "0.5, 20.5"})
  void addBalance(double value, double expected) {
    var user = getUser();
    assertEquals(expected, user.addBalance(value).getBalance());
  }
}
