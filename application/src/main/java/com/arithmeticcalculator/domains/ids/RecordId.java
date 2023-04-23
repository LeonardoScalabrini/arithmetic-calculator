package com.arithmeticcalculator.domains.ids;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class RecordId extends Id {
  private RecordId() {
    super();
  }

  private RecordId(String id) {
    super(id);
  }

  public static RecordId newInstance() {
    return new RecordId();
  }

  public static RecordId getInstance(String id) {
    return new RecordId(id);
  }
}
