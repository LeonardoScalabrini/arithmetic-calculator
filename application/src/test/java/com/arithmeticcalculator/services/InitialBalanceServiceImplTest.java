package com.arithmeticcalculator.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.fixtures.Fixture;
import com.arithmeticcalculator.ports.in.InitialBalanceService;
import com.arithmeticcalculator.ports.out.FindInitalBalanceInterface;
import com.arithmeticcalculator.ports.out.UserRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InitialBalanceServiceImplTest {
  @Mock private UserRepositoryInterface userRepository = mock(UserRepositoryInterface.class);

  @Mock
  private FindInitalBalanceInterface findInitalBalance = mock(FindInitalBalanceInterface.class);

  private InitialBalanceService initialBalanceService;
  @Mock private User user = mock(User.class);
  private final User newUser = Fixture.getUser();

  @BeforeEach
  void setUp() {
    initialBalanceService =
        InitialBalanceServiceImpl.newInstance(findInitalBalance, userRepository);
  }

  @Test
  void apply() {
    double balance = 5;
    when(user.addBalance(balance)).thenReturn(newUser);
    when(findInitalBalance.find()).thenReturn(balance);
    doNothing().when(userRepository).save(newUser);
    initialBalanceService.apply(user);
    verify(user, times(1)).addBalance(balance);
    verify(findInitalBalance, times(1)).find();
    verify(userRepository, times(1)).save(newUser);
  }

  @Test
  void newInstance() {
    assertNotSame(
        initialBalanceService,
        InitialBalanceServiceImpl.newInstance(findInitalBalance, userRepository));
  }
}
