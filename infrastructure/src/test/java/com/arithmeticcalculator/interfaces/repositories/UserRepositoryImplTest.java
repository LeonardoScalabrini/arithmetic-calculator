package com.arithmeticcalculator.interfaces.repositories;

import static com.arithmeticcalculator.fixtures.Fixture.getUser;
import static com.arithmeticcalculator.fixtures.Fixture.getUserEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.arithmeticcalculator.interfaces.repositories.entities.UserEntity;
import com.arithmeticcalculator.interfaces.repositories.jpa.UserEntityJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserRepositoryImplTest {

  @Mock private UserEntityJpaRepository userEntityJpaRepository;
  private UserRepositoryImpl userRepository;
  private final UserEntity userEntity = getUserEntity();

  @BeforeEach
  void init() {
    userRepository = new UserRepositoryImpl(userEntityJpaRepository);
  }

  @Test
  void save() {
    when(userEntityJpaRepository.save(any())).thenReturn(userEntity);
    var user = getUser();
    userRepository.save(user);
    verify(userEntityJpaRepository, times(1)).save(any());
  }

  @Test
  void findByEmail() {
    when(userEntityJpaRepository.findByEmail(ArgumentMatchers.anyString()))
        .thenReturn(Optional.of(userEntity));
    var result = userRepository.findByEmail("email").orElseThrow();
    assertEquals(userEntity.getBalance(), result.getBalance());
    assertEquals(userEntity.getEmail(), result.getEmail());
    verify(userEntityJpaRepository, times(1)).findByEmail(ArgumentMatchers.anyString());
  }

  @Test
  void empty() {
    when(userEntityJpaRepository.findByEmail(ArgumentMatchers.anyString()))
        .thenReturn(Optional.empty());
    var result = userRepository.findByEmail("email");
    assertTrue(result.isEmpty());
    verify(userEntityJpaRepository, times(1)).findByEmail(ArgumentMatchers.anyString());
  }
}
