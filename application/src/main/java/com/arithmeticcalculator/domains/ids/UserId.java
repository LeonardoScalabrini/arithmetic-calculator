package com.arithmeticcalculator.domains.ids;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class UserId extends Id {
  private UserId() {
    super();
  }

  private UserId(String id) {
    super(id);
  }

  public static UserId newInstance() {
    return new UserId();
  }

  public static UserId getInstance(String id) {
    return new UserId(id);
  }
}
