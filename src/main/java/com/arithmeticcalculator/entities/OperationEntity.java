package com.arithmeticcalculator.entities;

import com.arithmeticcalculator.domains.Operations;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Immutable
@Entity(name = "operation")
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class OperationEntity implements Serializable {
  @Id @EqualsAndHashCode.Exclude @Builder.Default private String id = UUID.randomUUID().toString();
  @NotNull private Operations type;
  private double cost;

  private OperationEntity() {};

  private OperationEntity(@NonNull String id, @NonNull Operations type, double cost) {
    this.id = id;
    this.type = type;
    this.cost = cost;
  }
}
