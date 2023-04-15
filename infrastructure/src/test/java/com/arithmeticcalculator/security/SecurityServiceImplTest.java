package com.arithmeticcalculator.security;

import static com.arithmeticcalculator.fixtures.Fixture.getUserEntity;
import static org.mockito.Mockito.*;

import com.arithmeticcalculator.entities.UserEntity;
import com.arithmeticcalculator.repositories.jpa.UserEntityJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class SecurityServiceImplTest {

  @Mock private UserEntityJpaRepository userEntityJpaRepository;
  @InjectMocks private SecurityServiceImpl securityService;

  private final UserEntity userEntity = getUserEntity();

  @BeforeEach
  void setUp() {
    when(userEntityJpaRepository.save(userEntity)).thenReturn(userEntity);
  }

  @Test
  void createUser() {
    securityService.createUser("email", "password");
    verify(userEntityJpaRepository, times(1)).save(any());
  }
}
