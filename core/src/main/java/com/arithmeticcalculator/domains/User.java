package com.arithmeticcalculator.domains;

import static com.arithmeticcalculator.domains.exceptions.IllegalStateExceptionFactory.getInstance;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class User {
  private final String email;
  private final double balance;

  private User(@NonNull String email, double balance) {
    this.email = email;
    this.balance = balance;
  }

  public User pay(@NonNull Operation operation) {
    if (operation.getCost() > balance)
      throw getInstance()
          .param("operation", operation)
          .message("The balance is not enough for the operation")
          .build();
    return new User(this.email, balance - operation.getCost());
  }
}
