package com.arithmeticcalculator.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.security.Privileges;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class UserEntityTest {

  @Test
  void build() {
    var userEntity =
        UserEntity.builder()
            .name("name")
            .email("email")
            .password("password")
            .privileges(Privileges.USER)
            .build();
    assertNotNull(userEntity.getId());
    assertEquals("name", userEntity.getName());
    assertEquals("email", userEntity.getEmail());
    assertEquals("password", userEntity.getPassword());
    assertEquals(Privileges.USER, userEntity.getPrivileges());
    EqualsVerifier.forClass(UserEntity.class).suppress(Warning.STRICT_INHERITANCE).verify();
    ToStringVerifier.forClass(UserEntity.class).verify();
  }

  @Test
  void notNull() {
    assertThrows(
        NullPointerException.class,
        () ->
            UserEntity.builder()
                .email("email")
                .password("password")
                .privileges(Privileges.USER)
                .build());
    assertThrows(
        NullPointerException.class,
        () ->
            UserEntity.builder()
                .name("name")
                .password("password")
                .privileges(Privileges.USER)
                .build());
    assertThrows(
        NullPointerException.class,
        () -> UserEntity.builder().name("name").email("email").privileges(Privileges.USER).build());
    assertThrows(
        NullPointerException.class,
        () -> UserEntity.builder().name("name").email("email").password("password").build());
  }
}
