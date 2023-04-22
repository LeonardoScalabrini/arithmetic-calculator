package com.arithmeticcalculator.domains;

import com.arithmeticcalculator.domains.ids.UserId;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class User {
  private final UserId userId;
  private final String email;
  private final double balance;
  private final Privileges privileges = Privileges.USER;
  private final Password password;

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
