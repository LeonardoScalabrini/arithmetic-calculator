package com.arithmeticcalculator.domains.ids;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public abstract class Id {
  private final String id;

  protected Id() {
    this.id = UUID.randomUUID().toString();
  }

  protected Id(@NonNull String id) {
    this.id = UUID.fromString(id).toString();
  }

  public String getId() {
    return id;
  }
}
