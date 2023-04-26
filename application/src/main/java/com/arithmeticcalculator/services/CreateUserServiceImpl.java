package com.arithmeticcalculator.services;

import com.arithmeticcalculator.domains.Password;
import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.domains.ids.UserId;
import com.arithmeticcalculator.ports.in.CreateUserService;
import com.arithmeticcalculator.ports.out.FindInitalBalanceInterface;
import com.arithmeticcalculator.ports.out.UserRepositoryInterface;
import lombok.NonNull;
import lombok.Value;

@Value
public class CreateUserServiceImpl implements CreateUserService {

  UserRepositoryInterface userRepositoryInterface;
  FindInitalBalanceInterface findInitalBalanceInterface;

  private CreateUserServiceImpl(
      @NonNull UserRepositoryInterface userRepositoryInterface,
      @NonNull FindInitalBalanceInterface findInitalBalanceInterface) {
    this.userRepositoryInterface = userRepositoryInterface;
    this.findInitalBalanceInterface = findInitalBalanceInterface;
  }

  @Override
  public void create(@NonNull String email, @NonNull String password) {
    var user =
        User.newInstance(
            UserId.newInstance(),
            email,
            findInitalBalanceInterface.find(),
            Password.newInstance(password));
    userRepositoryInterface.save(user);
  }

  public static CreateUserServiceImpl newInstance(
      @NonNull UserRepositoryInterface userRepositoryInterface,
      @NonNull FindInitalBalanceInterface findInitalBalanceInterface) {
    return new CreateUserServiceImpl(userRepositoryInterface, findInitalBalanceInterface);
  }
}
