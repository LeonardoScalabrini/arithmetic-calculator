package com.arithmeticcalculator.usercases;

import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.domains.interfaces.FindInitalBalance;
import com.arithmeticcalculator.domains.interfaces.UserRepository;
import com.arithmeticcalculator.fixtures.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class InitialBalanceUserCaseImplTest {
  @Mock private UserRepository userRepository;

  @Mock private FindInitalBalance findInitalBalance;
  @InjectMocks private InitialBalanceUserCaseImpl initialBalanceUserCase;

  private final User user = Fixture.getUser();

  @BeforeEach
  void setUp() {
    Mockito.when(findInitalBalance.find()).thenReturn(user.getBalance());
    Mockito.doNothing().when(userRepository).save(user);
  }

  @Test
  void apply() {
    initialBalanceUserCase.apply("email");
    Mockito.verify(findInitalBalance, Mockito.times(1)).find();
    Mockito.verify(userRepository, Mockito.times(1)).save(user);
  }
}
