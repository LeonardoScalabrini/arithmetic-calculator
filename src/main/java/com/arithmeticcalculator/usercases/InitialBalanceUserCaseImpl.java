package com.arithmeticcalculator.usercases;

import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.usercases.interfaces.InitialBalanceUserCase;
import com.arithmeticcalculator.usercases.interfaces.repositories.UserRepository;
import com.arithmeticcalculator.usercases.interfaces.services.FindInitalBalance;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class InitialBalanceUserCaseImpl implements InitialBalanceUserCase {
  private final FindInitalBalance findInitalBalance;
  private final UserRepository userRepository;

  @Autowired
  public InitialBalanceUserCaseImpl(
      @NonNull FindInitalBalance findInitalBalance, @NonNull UserRepository userRepository) {
    this.findInitalBalance = findInitalBalance;
    this.userRepository = userRepository;
  }

  @Override
  public void apply(@NonNull String email) {
    userRepository.save(User.builder().email(email).balance(findInitalBalance.find()).build());
  }
}
