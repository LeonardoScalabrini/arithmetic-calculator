package com.arithmeticcalculator.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.arithmeticcalculator.entities.UserEntity;
import com.arithmeticcalculator.repositories.UserEntityJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserDetailsServiceImplTest {
  @Mock private UserEntityJpaRepository userEntityJpaRepository;
  @InjectMocks private UserDetailsServiceImpl userDetailsService;

  @BeforeEach
  void setUp() {
    Mockito.when(userEntityJpaRepository.findByEmail("email"))
        .thenReturn(
            Optional.of(
                UserEntity.builder()
                    .name("name")
                    .email("email")
                    .password("password")
                    .privileges(Privileges.USER)
                    .build()));
  }

  @Test
  void loadUserByUsername() {
    var result = userDetailsService.loadUserByUsername("email");
    assertEquals("email", result.getUsername());
    assertEquals("password", result.getPassword());
    assertEquals("USER", result.getAuthorities().stream().findFirst().orElseThrow().getAuthority());
    verify(userEntityJpaRepository, times(1)).findByEmail("email");
  }

  @Test
  void sholdThrow() {
    Mockito.when(userEntityJpaRepository.findByEmail("email")).thenReturn(Optional.empty());
    assertThrows(
        UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("email"));
    verify(userEntityJpaRepository, times(1)).findByEmail("email");
  }
}
