package com.arithmeticcalculator.api.v1.dtos;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.fixtures.Fixture;
import com.jparams.verifier.tostring.ToStringVerifier;
import java.io.IOException;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class UserCreateRequestDTOTest {

  @Test
  void build() {
    var dto = new UserCreateRequestDTO("email", "password");
    assertEquals("email", dto.getEmail());
    assertEquals("password", dto.getPassword());
    EqualsVerifier.forClass(UserCreateRequestDTO.class)
        .suppress(Warning.STRICT_INHERITANCE)
        .verify();
    ToStringVerifier.forClass(UserCreateRequestDTO.class).verify();
  }

  @Test
  void fromJson() throws IOException {
    var request = new UserCreateRequestDTO("email", "password");
    var result = Fixture.readValue(Fixture.CREATE_USER_REQUEST_PATH, UserCreateRequestDTO.class);
    assertEquals(request, result);
  }
}
