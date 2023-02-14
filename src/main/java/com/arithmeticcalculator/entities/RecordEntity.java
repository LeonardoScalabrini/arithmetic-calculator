package com.arithmeticcalculator.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Immutable
@Entity(name = "records")
@Getter
@ToString
@EqualsAndHashCode
@Builder
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

  private Date date;

  public RecordEntity() {};

  private RecordEntity(
      @NonNull UserEntity user,
      @NonNull OperationEntity operation,
      double amount,
      double userBalance,
      @NonNull String operationResponse,
      @NonNull Date date) {
    this.user = user;
    this.operation = operation;
    this.amount = amount;
    this.userBalance = userBalance;
    this.operationResponse = operationResponse;
    this.date = date;
  }
}
