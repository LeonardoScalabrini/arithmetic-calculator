package com.arithmeticcalculator.entities;

import com.arithmeticcalculator.domains.Operation;
import com.arithmeticcalculator.domains.OperationTypes;
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
  private OperationTypes type;

  private double cost;

  public OperationEntity() {};

  private OperationEntity(@NonNull OperationTypes type, double cost) {
    this.type = type;
    this.cost = cost;
  }

  public static OperationEntity from(@NonNull Operation operation) {
    return new OperationEntity(operation.getOperationTypes(), operation.getCost());
  }

  public Operation getOperation() {
    return Operation.builder().operationTypes(type).cost(cost).build();
  }
}
