package com.arithmeticcalculator.entities;

import com.arithmeticcalculator.domains.CostOperation;
import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.domains.ids.CostOperationId;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Immutable
@Entity(name = "operations")
@Getter
@ToString
@EqualsAndHashCode
public final class OperationEntity {
  @EmbeddedId @EqualsAndHashCode.Exclude private CostOperationId id;

  @NotNull
  @Enumerated(EnumType.STRING)
  private OperationTypes type;

  private double cost;

  public OperationEntity() {};

  private OperationEntity(
      @NonNull CostOperationId costOperationId, @NonNull OperationTypes type, double cost) {
    this.id = costOperationId;
    this.type = type;
    this.cost = cost;
  }

  public static OperationEntity from(@NonNull CostOperation costOperation) {
    return new OperationEntity(
        costOperation.getCostOperationId(),
        costOperation.getOperationTypes(),
        costOperation.getCost());
  }

  public CostOperation getOperation() {
    return CostOperation.builder().costOperationId(id).operationTypes(type).cost(cost).build();
  }
}
