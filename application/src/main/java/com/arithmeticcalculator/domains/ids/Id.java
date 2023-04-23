package com.arithmeticcalculator.domains.ids;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public abstract class Id {
  private final UUID id;

  protected Id() {
    this.id = UUID.randomUUID();
  }

  protected Id(String id) {
    this.id = UUID.fromString(id);
  }

  public String getId() {
    return id.toString();
  }
}
