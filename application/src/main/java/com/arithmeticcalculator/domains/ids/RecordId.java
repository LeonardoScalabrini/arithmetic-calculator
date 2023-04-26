package com.arithmeticcalculator.domains.ids;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RecordId extends Id {
  private RecordId() {
    super();
  }

  private RecordId(@NonNull String id) {
    super(id);
  }

  public static RecordId newInstance() {
    return new RecordId();
  }

  public static RecordId getInstance(@NonNull String id) {
    return new RecordId(id);
  }
}
