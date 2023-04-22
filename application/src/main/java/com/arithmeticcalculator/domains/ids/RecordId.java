package com.arithmeticcalculator.domains.ids;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class RecordId extends Id {
  private RecordId() {}

  public static RecordId newInstance() {
    return new RecordId();
  }
}
