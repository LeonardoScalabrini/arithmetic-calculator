package com.arithmeticcalculator.interfaces.repositories.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.domains.Privileges;
import com.arithmeticcalculator.fixtures.Fixture;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class UserEntityTest {

  @Test
  void build() {
    var userEntity = Fixture.getUserEntity();
    assertNotNull(userEntity.getId());
    assertEquals("email", userEntity.getEmail());
    assertEquals("password", userEntity.getPassword());
    assertEquals(Privileges.USER, userEntity.getPrivileges());
    assertEquals(20, userEntity.getBalance());
    EqualsVerifier.forClass(UserEntity.class).verify();
    ToStringVerifier.forClass(UserEntity.class).verify();
  }

  @Test
  void notNull() {
    assertThrows(NullPointerException.class, () -> UserEntity.builder().build());
  }
}
