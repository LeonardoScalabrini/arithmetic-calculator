package com.arithmeticcalculator.domains.ids;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserIdTest {

  @Test
  void newInstance() {
    var id = UserId.newInstance();
    assertNotNull(id);
    assertNotNull(id.getId());
    assertNotEquals(id, UserId.newInstance());
  }
}
