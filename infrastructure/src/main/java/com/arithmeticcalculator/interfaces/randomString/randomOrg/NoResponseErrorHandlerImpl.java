package com.arithmeticcalculator.interfaces.randomString.randomOrg;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.client.ResponseErrorHandler;

public final class NoResponseErrorHandlerImpl implements ResponseErrorHandler {

  private static final ResponseErrorHandler instance = new NoResponseErrorHandlerImpl();

  private NoResponseErrorHandlerImpl() {}

  public static ResponseErrorHandler getInstance() {
    return instance;
  }

  @Override
  public boolean hasError(@Nullable ClientHttpResponse response) {
    return false;
  }

  @Override
  public void handleError(@Nullable ClientHttpResponse response) {}
}
