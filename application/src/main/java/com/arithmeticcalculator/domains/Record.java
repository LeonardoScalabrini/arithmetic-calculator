package com.arithmeticcalculator.domains;

import com.arithmeticcalculator.domains.ids.RecordId;
import com.arithmeticcalculator.domains.ids.UserId;
import java.time.Instant;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class Record<T> {
  RecordId recordId;
  UserId userId;
  OperationTypes operationTypes;
  double amount;
  double balance;
  T operationResult;
  Instant date;

  private Record(
      @NonNull RecordId recordId,
      @NonNull UserId userId,
      @NonNull OperationTypes operationTypes,
      double amount,
      double balance,
      @NonNull T operationResult,
      @NonNull Instant date) {
    this.recordId = recordId;
    this.userId = userId;
    this.operationTypes = operationTypes;
    this.amount = amount;
    this.balance = balance;
    this.operationResult = operationResult;
    this.date = date;
  }

  public static <T> Record<T> from(
      @NonNull User user, @NonNull CostOperation costOperation, @NonNull T result) {
    return new Record<>(
        RecordId.newInstance(),
        user.getUserId(),
        costOperation.getOperationTypes(),
        costOperation.getCost(),
        user.getBalance(),
        result,
        Instant.now());
  }
}
