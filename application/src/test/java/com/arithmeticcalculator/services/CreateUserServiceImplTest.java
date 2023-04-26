package com.arithmeticcalculator.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.arithmeticcalculator.abstracts.UtilsTest;
import com.arithmeticcalculator.ports.in.CreateUserService;
import com.arithmeticcalculator.ports.out.FindInitalBalanceInterface;
import com.arithmeticcalculator.ports.out.UserRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceImplTest extends UtilsTest {

  private final UserRepositoryInterface userRepositoryInterface =
      mock(UserRepositoryInterface.class);
  private final FindInitalBalanceInterface findInitalBalanceInterface =
      mock(FindInitalBalanceInterface.class);
  private CreateUserService createUserService;

  @BeforeEach
  void setUp() {
    when(findInitalBalanceInterface.find()).thenReturn(20.0);
    doNothing().when(userRepositoryInterface).save(any());
    createUserService =
        CreateUserServiceImpl.newInstance(userRepositoryInterface, findInitalBalanceInterface);
  }

  @Test
  void createUserServiceImpl() {
    assertClass(
        CreateUserServiceImpl.class,
        CreateUserServiceImpl.newInstance(userRepositoryInterface, findInitalBalanceInterface));
  }

  @Test
  void create() {
    createUserService.create("email", "password");
    verify(findInitalBalanceInterface, times(1)).find();
    verify(userRepositoryInterface, times(1)).save(any());
  }

  @Test
  void newInstance() {
    assertNotSame(
        createUserService,
        CreateUserServiceImpl.newInstance(userRepositoryInterface, findInitalBalanceInterface));
  }
}
