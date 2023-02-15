package com.arithmeticcalculator.configurations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = InitialBalanceConfigurationFind.class)
class InitialBalanceConfigurationTest {

  @Autowired private InitialBalanceConfigurationFind initialBalanceConfiguration;

  @Test
  void find() {
    assertEquals(100, initialBalanceConfiguration.find());
  }
}
