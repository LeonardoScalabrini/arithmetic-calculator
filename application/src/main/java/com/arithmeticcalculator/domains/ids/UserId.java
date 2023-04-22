package com.arithmeticcalculator.domains.ids;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class UserId extends Id {
  private UserId() {}

  public static UserId newInstance() {
    return new UserId();
  }
}
