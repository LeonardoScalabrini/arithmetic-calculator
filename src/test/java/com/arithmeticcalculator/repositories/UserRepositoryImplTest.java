package com.arithmeticcalculator.repositories;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.entities.UserEntity;
import com.arithmeticcalculator.fixtures.Fixture;
import com.arithmeticcalculator.repositories.jpa.UserEntityJpaRepository;
import com.arithmeticcalculator.security.Privileges;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserRepositoryImplTest {

  @Mock private UserEntityJpaRepository userEntityJpaRepository;
  @InjectMocks private UserRepositoryImpl userRepository;

  private final UserEntity userEntity = Fixture.getUserEntity();

  @BeforeEach
  void setUp() {
    Mockito.when(userEntityJpaRepository.findByEmail(ArgumentMatchers.anyString()))
        .thenReturn(Optional.of(userEntity));
    Mockito.when(userEntityJpaRepository.save(userEntity)).thenReturn(userEntity);
  }

  @Test
  void save() {
    var user = Fixture.getUser();
    userRepository.save(user);
    Mockito.verify(userEntityJpaRepository, Mockito.times(1))
        .findByEmail(ArgumentMatchers.anyString());
    Mockito.verify(userEntityJpaRepository, Mockito.times(1)).save(userEntity);
  }

  @Test
  void notFound() {
    Mockito.when(userEntityJpaRepository.findByEmail(ArgumentMatchers.anyString()))
        .thenReturn(Optional.empty());
    var user = Fixture.getUser();
    userRepository.save(user);
    Mockito.verify(userEntityJpaRepository, Mockito.times(1))
        .findByEmail(ArgumentMatchers.anyString());
    Mockito.verify(userEntityJpaRepository, Mockito.times(0)).save(userEntity);
  }

  @Test
  void findByEmail() {
    var result = userRepository.findByEmail("email").orElseThrow();
    assertEquals(userEntity.getBalance(), result.getBalance());
    assertEquals(userEntity.getEmail(), result.getEmail());
    Mockito.verify(userEntityJpaRepository, Mockito.times(1))
        .findByEmail(ArgumentMatchers.anyString());
  }

  @Test
  void empty() {
    Mockito.when(userEntityJpaRepository.findByEmail(ArgumentMatchers.anyString()))
        .thenReturn(Optional.empty());
    var result = userRepository.findByEmail("email");
    assertTrue(result.isEmpty());
    Mockito.verify(userEntityJpaRepository, Mockito.times(1))
        .findByEmail(ArgumentMatchers.anyString());
  }
}
