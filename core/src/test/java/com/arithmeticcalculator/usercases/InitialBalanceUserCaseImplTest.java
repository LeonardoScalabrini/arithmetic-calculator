package com.arithmeticcalculator.usercases;

import static org.mockito.Mockito.*;

import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.fixtures.Fixture;
import com.arithmeticcalculator.usercases.interfaces.repositories.UserRepository;
import com.arithmeticcalculator.usercases.interfaces.services.FindInitalBalance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class InitialBalanceUserCaseImplTest {
  @Mock private UserRepository userRepository = mock(UserRepository.class);

  @Mock private FindInitalBalance findInitalBalance = mock(FindInitalBalance.class);
  private InitialBalanceUserCaseImpl initialBalanceUserCase;

  private final User user = Fixture.getUser();

  @BeforeEach
  void setUp() {
    when(findInitalBalance.find()).thenReturn(user.getBalance());
    doNothing().when(userRepository).save(user);
    initialBalanceUserCase = new InitialBalanceUserCaseImpl(findInitalBalance, userRepository);
  }

  @Test
  void apply() {
    initialBalanceUserCase.apply("email");
    verify(findInitalBalance, times(1)).find();
    verify(userRepository, times(1)).save(user);
  }
}
