package com.arithmeticcalculator.ports.in;

import com.arithmeticcalculator.domains.User;

public interface InitialBalanceService {
  void apply(User user);
}
