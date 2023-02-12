package com.arithmeticcalculator.configurations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = RandomOrgConfigImpl.class)
class RandomOrgConfigImplTest {

  @Autowired private RandomOrgConfigImpl randomOrgConfig;

  @Test
  void getHost() {
    assertEquals("https://www.test.org", randomOrgConfig.getHost());
  }
}
