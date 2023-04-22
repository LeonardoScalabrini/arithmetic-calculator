package com.arithmeticcalculator.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrivilegesTest {

  @Test
  void getRole() {
    assertEquals("USER", Privileges.USER.getRole());
  }
}
