package com.arithmeticcalculator.repositories;

import static com.arithmeticcalculator.fixtures.Fixture.getUser;
import static com.arithmeticcalculator.fixtures.Fixture.getUserEntity;
import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.entities.UserEntity;
import com.arithmeticcalculator.repositories.jpa.UserEntityJpaRepository;
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

  private final UserEntity userEntity = getUserEntity();

  @BeforeEach
  void setUp() {
    Mockito.when(userEntityJpaRepository.findByEmail(ArgumentMatchers.anyString()))
        .thenReturn(Optional.of(userEntity));
    Mockito.when(userEntityJpaRepository.save(userEntity)).thenReturn(userEntity);
  }

  @Test
  void save() {
    var user = getUser();
    userRepository.save(user);
    Mockito.verify(userEntityJpaRepository, Mockito.times(1))
        .findByEmail(ArgumentMatchers.anyString());
    Mockito.verify(userEntityJpaRepository, Mockito.times(1)).save(userEntity);
  }

  @Test
  void notFound() {
    Mockito.when(userEntityJpaRepository.findByEmail(ArgumentMatchers.anyString()))
        .thenReturn(Optional.empty());
    var user = getUser();
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
