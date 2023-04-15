package com.arithmeticcalculator.domains.exceptions;

public final class IllegalStateExceptionFactory {
  private final StringBuilder stringBuilder = new StringBuilder();

  private IllegalStateExceptionFactory() {};

  public static IllegalStateExceptionFactory getInstance() {
    return new IllegalStateExceptionFactory();
  }

  public <T> IllegalStateExceptionFactory param(String param, T value) {
    stringBuilder.append(param).append(value.toString());
    return this;
  }

  public IllegalStateExceptionFactory message(String massage) {
    stringBuilder.append(massage);
    return this;
  }

  public IllegalStateException build() {
    return new IllegalStateException(stringBuilder.toString());
  }
}
