package com.arithmeticcalculator.configurations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = RandomOrgConfigImpl.class)
@TestPropertySource("classpath:application.properties")
class RandomOrgConfigImplTest {

  @Autowired private RandomOrgConfigImpl randomOrgConfig;

  @Test
  void getHost() {
    assertEquals("https://www.test.org", randomOrgConfig.getHost());
  }
}
