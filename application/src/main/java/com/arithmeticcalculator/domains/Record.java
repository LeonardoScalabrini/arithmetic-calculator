package com.arithmeticcalculator.domains;

import com.arithmeticcalculator.domains.ids.RecordId;
import com.arithmeticcalculator.domains.ids.UserId;
import java.time.Instant;
import lombok.*;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Record<T> {
  RecordId recordId;
  UserId userId;
  OperationTypes operationTypes;
  double amount;
  double balance;
  T operationResult;
  Instant date;

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
