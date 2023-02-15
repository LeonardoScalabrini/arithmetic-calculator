package com.arithmeticcalculator.domains;

import java.util.Date;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
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

  public static <T> Record<T> from(
      @NonNull User user, @NonNull Operation operation, @NonNull T result) {
    return new Record<>(user, operation, operation.getCost(), user.getBalance(), result);
  }
}
