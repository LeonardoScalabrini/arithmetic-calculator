package com.arithmeticcalculator.services;

import com.arithmeticcalculator.domains.Password;
import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.domains.ids.UserId;
import com.arithmeticcalculator.ports.in.CreateUserService;
import com.arithmeticcalculator.ports.out.FindInitalBalanceInterface;
import com.arithmeticcalculator.ports.out.UserRepositoryInterface;
import lombok.NonNull;

public class CreateUserServiceImpl implements CreateUserService {

  private final UserRepositoryInterface userRepositoryInterface;
  private final FindInitalBalanceInterface findInitalBalanceInterface;

  private CreateUserServiceImpl(
      @NonNull UserRepositoryInterface userRepositoryInterface,
      @NonNull FindInitalBalanceInterface findInitalBalanceInterface) {
    this.userRepositoryInterface = userRepositoryInterface;
    this.findInitalBalanceInterface = findInitalBalanceInterface;
  }

  @Override
  public void create(@NonNull String email, @NonNull String password) {
    var user =
        User.builder()
            .userId(UserId.newInstance())
            .email(email)
            .password(Password.newInstance(password))
            .balance(findInitalBalanceInterface.find())
            .build();
    userRepositoryInterface.save(user);
  }

  public static CreateUserService newInstance(
      @NonNull UserRepositoryInterface userRepositoryInterface,
      @NonNull FindInitalBalanceInterface findInitalBalanceInterface) {
    return new CreateUserServiceImpl(userRepositoryInterface, findInitalBalanceInterface);
  }
}
