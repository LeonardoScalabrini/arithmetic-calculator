package com.arithmeticcalculator.security;

public enum Privileges {
  USER("USER");

  private final String role;

  Privileges(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }
}
