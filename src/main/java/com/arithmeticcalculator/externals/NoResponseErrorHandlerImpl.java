package com.arithmeticcalculator.externals;

import java.util.Objects;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public final class NoResponseErrorHandlerImpl implements ResponseErrorHandler {

  private static ResponseErrorHandler instance;

  private NoResponseErrorHandlerImpl() {}

  public static ResponseErrorHandler getInstance() {
    if (Objects.isNull(instance)) instance = new NoResponseErrorHandlerImpl();
    return instance;
  }

  @Override
  public boolean hasError(ClientHttpResponse response) {
    return false;
  }

  @Override
  public void handleError(ClientHttpResponse response) {}
}
