package com.arithmeticcalculator.security;

import lombok.NonNull;

public enum Privileges {
  USER("USER");
  private final String role;

  Privileges(@NonNull String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }
}
