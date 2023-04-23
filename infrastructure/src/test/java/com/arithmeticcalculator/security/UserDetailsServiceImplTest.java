package com.arithmeticcalculator.security;

import static com.arithmeticcalculator.fixtures.Fixture.getUserEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.arithmeticcalculator.interfaces.repositories.entities.UserEntity;
import com.arithmeticcalculator.interfaces.repositories.jpa.UserEntityJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserDetailsServiceImplTest {
  @Mock private UserEntityJpaRepository userEntityJpaRepository;
  @InjectMocks private UserDetailsServiceImpl userDetailsService;

  private final UserEntity userEntity = getUserEntity();

  @BeforeEach
  void setUp() {
    when(userEntityJpaRepository.findByEmail("email")).thenReturn(Optional.of(userEntity));
  }

  @Test
  void loadUserByUsername() {
    var result = userDetailsService.loadUserByUsername("email");
    assertEquals(userEntity.getEmail(), result.getUsername());
    assertEquals(userEntity.getPassword(), result.getPassword());
    assertEquals("USER", result.getAuthorities().stream().findFirst().orElseThrow().getAuthority());
    verify(userEntityJpaRepository, times(1)).findByEmail("email");
  }

  @Test
  void sholdThrow() {
    when(userEntityJpaRepository.findByEmail("email")).thenReturn(Optional.empty());
    assertThrows(
        UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("email"));
    verify(userEntityJpaRepository, times(1)).findByEmail("email");
  }
}
