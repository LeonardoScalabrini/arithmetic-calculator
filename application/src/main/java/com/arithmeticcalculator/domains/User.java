package com.arithmeticcalculator.domains;

import com.arithmeticcalculator.domains.exceptions.IllegalStateExceptionFactory;
import com.arithmeticcalculator.domains.ids.UserId;
import lombok.*;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
  UserId userId;
  String email;
  double balance;
  Privileges privileges = Privileges.USER;
  Password password;

  private void validateValue(double value) {
    if (value >= 0) return;
    throw IllegalStateExceptionFactory.builder(getClass()).param("value", value).build();
  }

  public User pay(double value) {
    validateValue(value);
    return new User(userId, email, balance - value, password);
  }

  public User addBalance(double value) {
    validateValue(value);
    return new User(userId, email, this.balance + value, password);
  }

  public static User newInstance(
      @NonNull UserId userId, @NonNull String email, double balance, @NonNull Password password) {
    return new User(userId, email, balance, password);
  }
}
