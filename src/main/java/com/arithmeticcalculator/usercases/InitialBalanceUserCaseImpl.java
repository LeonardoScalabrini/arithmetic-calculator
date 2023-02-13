package com.arithmeticcalculator.usercases;

import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.domains.interfaces.FindInitalBalance;
import com.arithmeticcalculator.domains.interfaces.InitialBalanceUserCase;
import com.arithmeticcalculator.domains.interfaces.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitialBalanceUserCaseImpl implements InitialBalanceUserCase {
  private final FindInitalBalance findInitalBalance;
  private final UserRepository userRepository;

  @Autowired
  public InitialBalanceUserCaseImpl(
      FindInitalBalance findInitalBalance, UserRepository userRepository) {
    this.findInitalBalance = findInitalBalance;
    this.userRepository = userRepository;
  }

  @Override
  public void apply(@NonNull String email) {
    var initial = findInitalBalance.find();
    userRepository.save(User.builder().email(email).balance(initial).build());
  }
}
