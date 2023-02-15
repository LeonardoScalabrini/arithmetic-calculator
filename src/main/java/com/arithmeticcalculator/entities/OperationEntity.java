package com.arithmeticcalculator.entities;

import com.arithmeticcalculator.domains.Operation;
import com.arithmeticcalculator.domains.Operations;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Immutable
@Entity(name = "operations")
@Getter
@ToString
@EqualsAndHashCode
public class OperationEntity implements Serializable {
  @Id @EqualsAndHashCode.Exclude private final String id = UUID.randomUUID().toString();

  @NotNull
  @Enumerated(EnumType.STRING)
  private Operations type;

  private double cost;

  public OperationEntity() {};

  private OperationEntity(@NonNull Operations type, double cost) {
    this.type = type;
    this.cost = cost;
  }

  public static OperationEntity from(@NonNull Operation operation) {
    return new OperationEntity(operation.getOperations(), operation.getCost());
  }

  public Operation getOperation() {
    return Operation.builder().operations(type).cost(cost).build();
  }
}
