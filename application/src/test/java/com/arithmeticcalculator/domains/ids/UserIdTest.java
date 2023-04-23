package com.arithmeticcalculator.domains.ids;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class UserIdTest {

  @Test
  void newInstance() {
    var id = UserId.newInstance();
    assertNotNull(id);
    assertNotNull(id.getId());
    assertNotEquals(id, UserId.newInstance());
  }

  @Test
  void getInstance() {
    var id = UUID.randomUUID().toString();
    var instance = UserId.getInstance(id).getId();
    assertEquals(id, instance);
  }
}
