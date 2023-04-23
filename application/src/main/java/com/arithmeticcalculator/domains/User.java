package com.arithmeticcalculator.domains;

import com.arithmeticcalculator.domains.ids.UserId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class User {
  UserId userId;
  String email;
  double balance;
  Privileges privileges = Privileges.USER;
  Password password;

  private User(
      @NonNull UserId userId, @NonNull String email, double balance, @NonNull Password password) {
    this.userId = userId;
    this.email = email;
    this.balance = balance;
    this.password = password;
  }

  public User pay(double value) {
    return new User(userId, email, balance - value, password);
  }

  public User addBalance(double value) {
    return new User(userId, email, this.balance + value, password);
  }
}
