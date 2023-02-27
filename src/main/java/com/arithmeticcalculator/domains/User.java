package com.arithmeticcalculator.domains;

import com.arithmeticcalculator.domains.exceptions.OperationException;
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

  public User pay(@NonNull Operation operation) throws OperationException {
    if (operation.getCost() > balance)
      throw OperationException.withMessage("The balance is not enough for the operation");
    return new User(this.email, balance - operation.getCost());
  }
}
