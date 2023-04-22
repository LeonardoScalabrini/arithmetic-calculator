package com.arithmeticcalculator.services;

import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.ports.in.InitialBalanceService;
import com.arithmeticcalculator.ports.out.FindInitalBalanceInterface;
import com.arithmeticcalculator.ports.out.UserRepositoryInterface;
import lombok.NonNull;

public final class InitialBalanceServiceImpl implements InitialBalanceService {
  private final FindInitalBalanceInterface findInitalBalance;
  private final UserRepositoryInterface userRepository;

  private InitialBalanceServiceImpl(
      @NonNull FindInitalBalanceInterface findInitalBalance,
      @NonNull UserRepositoryInterface userRepository) {
    this.findInitalBalance = findInitalBalance;
    this.userRepository = userRepository;
  }

  public void apply(@NonNull User user) {
    userRepository.save(user.addBalance(findInitalBalance.find()));
  }

  public static InitialBalanceService newInstance(
      @NonNull FindInitalBalanceInterface findInitalBalance,
      @NonNull UserRepositoryInterface userRepository) {
    return new InitialBalanceServiceImpl(findInitalBalance, userRepository);
  }
}
