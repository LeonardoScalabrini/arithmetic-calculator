package com.arithmeticcalculator.entities;

import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.ids.RecordId;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Immutable
@Entity(name = "records")
@Getter
@ToString
@EqualsAndHashCode
public final class RecordEntity {
  @EmbeddedId @EqualsAndHashCode.Exclude private RecordId id;

  @ManyToOne
  @JoinColumn(name = "fk_user")
  private UserEntity user;

  @NotNull
  @Enumerated(EnumType.STRING)
  private OperationTypes operation;

  @NotNull private double amount;

  @NotNull private double userBalance;

  @NotNull private String operationResponse;

  @NotNull private Instant date;

  public RecordEntity() {};

  private RecordEntity(
      @NonNull RecordId recordId,
      @NonNull UserEntity user,
      @NonNull OperationTypes operation,
      double amount,
      double userBalance,
      @NonNull String operationResponse,
      @NonNull Instant date) {
    this.id = recordId;
    this.user = user;
    this.operation = operation;
    this.amount = amount;
    this.userBalance = userBalance;
    this.operationResponse = operationResponse;
    this.date = date;
  }

  public static <T> RecordEntity from(@NonNull UserEntity user, @NonNull Record<T> record) {
    return new RecordEntity(
        record.getRecordId(),
        user,
        record.getOperationTypes(),
        record.getAmount(),
        record.getBalance(),
        record.getOperationResult().toString(),
        record.getDate());
  }
}
