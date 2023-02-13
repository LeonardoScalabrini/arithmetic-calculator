package com.arithmeticcalculator.configurations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = InitialBalanceConfiguration.class)
class InitialBalanceConfigurationTest {

  @Autowired private InitialBalanceConfiguration initialBalanceConfiguration;

  @Test
  void find() {
    assertEquals(100, initialBalanceConfiguration.find());
  }
}
