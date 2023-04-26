package com.arithmeticcalculator.domains.ids;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.abstracts.UtilsTest;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class UserIdTest extends UtilsTest {

  @Test
  void userId() {
    assertClass(UserId.class, UserId.newInstance());
    var id = UserId.newInstance();
    var uuid = UUID.randomUUID().toString();
    assertNotEquals(id, UserId.newInstance());
    assertEquals(id, UserId.getInstance(id.getId()));
    assertEquals(uuid, UserId.getInstance(uuid).getId());
  }
}
