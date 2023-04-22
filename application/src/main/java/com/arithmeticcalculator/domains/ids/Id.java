package com.arithmeticcalculator.domains.ids;

import java.util.UUID;
import lombok.Data;

@Data
public abstract class Id {
  private String id = UUID.randomUUID().toString();
}
