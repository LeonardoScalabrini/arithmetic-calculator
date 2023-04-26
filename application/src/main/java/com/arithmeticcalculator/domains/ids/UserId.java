package com.arithmeticcalculator.domains.ids;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserId extends Id {
  private UserId() {
    super();
  }

  private UserId(@NonNull String id) {
    super(id);
  }

  public static UserId newInstance() {
    return new UserId();
  }

  public static UserId getInstance(@NonNull String id) {
    return new UserId(id);
  }
}
