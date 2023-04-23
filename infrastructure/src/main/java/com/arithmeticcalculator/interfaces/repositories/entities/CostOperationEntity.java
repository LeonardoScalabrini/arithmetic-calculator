package com.arithmeticcalculator.interfaces.repositories.entities;

import com.arithmeticcalculator.domains.CostOperation;
import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.domains.ids.CostOperationId;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Immutable
@Entity(name = "costOperations")
@Getter
@ToString
@EqualsAndHashCode
public final class CostOperationEntity {
  @Id @EqualsAndHashCode.Exclude private String id;

  @NotNull
  @Enumerated(EnumType.STRING)
  private OperationTypes type;

  private double cost;

  public CostOperationEntity() {};

  private CostOperationEntity(@NonNull String id, @NonNull OperationTypes type, double cost) {
    this.id = id;
    this.type = type;
    this.cost = cost;
  }

  public static CostOperationEntity from(@NonNull CostOperation costOperation) {
    return new CostOperationEntity(
        costOperation.getCostOperationId().getId(),
        costOperation.getOperationTypes(),
        costOperation.getCost());
  }

  public CostOperation getOperation() {
    return CostOperation.builder()
        .costOperationId(CostOperationId.getInstance(id))
        .operationTypes(type)
        .cost(cost)
        .build();
  }
}
