package com.arithmeticcalculator.entities;

import com.arithmeticcalculator.domains.Record;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Immutable
@Entity(name = "records")
@Getter
@ToString
@EqualsAndHashCode
public class RecordEntity implements Serializable {
  @Id @EqualsAndHashCode.Exclude private final String id = UUID.randomUUID().toString();

  @ManyToOne
  @JoinColumn(name = "fk_user")
  private UserEntity user;

  @ManyToOne
  @JoinColumn(name = "fk_operation")
  private OperationEntity operation;

  private double amount;

  private double userBalance;

  private String operationResponse;

  private Instant date;

  public RecordEntity() {};

  private RecordEntity(
      @NonNull UserEntity user,
      @NonNull OperationEntity operation,
      double amount,
      double userBalance,
      @NonNull String operationResponse,
      @NonNull Instant date) {
    this.user = user;
    this.operation = operation;
    this.amount = amount;
    this.userBalance = userBalance;
    this.operationResponse = operationResponse;
    this.date = date;
  }

  public static <T> RecordEntity from(
      @NonNull UserEntity user, @NonNull OperationEntity operation, @NonNull Record<T> record) {
    return new RecordEntity(
        user,
        operation,
        record.getAmount(),
        record.getBalance(),
        record.getOperationResult().toString(),
        record.getDate());
  }
}
