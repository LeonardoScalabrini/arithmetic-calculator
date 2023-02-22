package com.arithmeticcalculator.domains;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import lombok.NonNull;

public enum Operations {
  ADDTION,
  SUBTRACTION,
  MULTIPLICATION,
  DIVISION,
  SQUARE_ROOT,
  RANDOM_STRING;
  private static HashMap<String, Operations> fromHashMap;

  private static void load() {
    if (fromHashMap != null) return;
    fromHashMap = new HashMap<>();
    Arrays.stream(Operations.values())
        .parallel()
        .forEach(operations -> fromHashMap.put(operations.name().toUpperCase(), operations));
  }

  public static Optional<Operations> from(@NonNull String name) {
    load();
    return Optional.ofNullable(fromHashMap.get(name.toUpperCase()));
  }

  public String getName() {
    return this.name().toUpperCase();
  }
}
