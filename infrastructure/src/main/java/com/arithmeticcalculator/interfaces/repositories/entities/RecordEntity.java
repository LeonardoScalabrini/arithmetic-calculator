package com.arithmeticcalculator.interfaces.repositories.entities;

import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.domains.Record;
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
  @Id @EqualsAndHashCode.Exclude private String id;

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
      @NonNull String id,
      @NonNull UserEntity user,
      @NonNull OperationTypes operation,
      double amount,
      double userBalance,
      @NonNull String operationResponse,
      @NonNull Instant date) {
    this.id = id;
    this.user = user;
    this.operation = operation;
    this.amount = amount;
    this.userBalance = userBalance;
    this.operationResponse = operationResponse;
    this.date = date;
  }

  public static <T> RecordEntity from(@NonNull UserEntity user, @NonNull Record<T> record) {
    return new RecordEntity(
        record.getRecordId().getId(),
        user,
        record.getOperationTypes(),
        record.getAmount(),
        record.getBalance(),
        record.getOperationResult().toString(),
        record.getDate());
  }
}
