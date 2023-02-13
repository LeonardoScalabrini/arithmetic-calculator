package com.arithmeticcalculator.domains;

import java.util.Date;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@Value
public class Record<T> {
  User user;
  Operation operation;
  double amount;
  double balance;
  T operationResult;
  Date date = new Date();

  private Record(
      @NonNull User user,
      @NonNull Operation operation,
      double amount,
      double balance,
      @NonNull T operationResult) {
    this.user = user;
    this.operation = operation;
    this.amount = amount;
    this.balance = balance;
    this.operationResult = operationResult;
  }
}
