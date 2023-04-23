package com.arithmeticcalculator.interfaces.repositories.jpa;

import static com.arithmeticcalculator.fixtures.Fixture.getUserEntity;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class UserEntityJpaRepositoryTest {
  @Autowired private UserEntityJpaRepository userEntityJpaRepository;

  @Test
  void save() {
    var userEntity = getUserEntity();
    var result = userEntityJpaRepository.save(userEntity);
    assertEquals(userEntity, result);
  }

  @Test
  void notSaveBlank() {
    assertThrows(
        RuntimeException.class,
        () -> userEntityJpaRepository.save(getUserEntity("emailPassword", "")));
    assertThrows(
        RuntimeException.class, () -> userEntityJpaRepository.save(getUserEntity("", "password")));
  }

  @Test
  void notSaveDuplicatedEmail() {
    userEntityJpaRepository.save(getUserEntity());
    assertThrows(RuntimeException.class, () -> userEntityJpaRepository.save(getUserEntity()));
  }

  @Test
  void findByEmail() {
    var userEntity = getUserEntity();
    userEntityJpaRepository.save(userEntity);
    var result = userEntityJpaRepository.findByEmail(userEntity.getEmail());
    assertEquals(userEntity, result.orElseThrow());
    assertTrue(userEntityJpaRepository.findByEmail("random").isEmpty());
  }
}
