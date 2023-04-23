package com.arithmeticcalculator.interfaces.randomString.randomOrg;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.ResponseErrorHandler;

class NoResponseErrorHandlerImplTest {

  private final ResponseErrorHandler noResponseErrorHandler =
      NoResponseErrorHandlerImpl.getInstance();

  @Test
  void hasError() throws IOException {
    assertFalse(noResponseErrorHandler.hasError(null));
  }

  @Test
  void handleError() {
    assertDoesNotThrow(() -> noResponseErrorHandler.handleError(null));
  }

  @Test
  void instance() {
    assertEquals(noResponseErrorHandler, NoResponseErrorHandlerImpl.getInstance());
  }
}
