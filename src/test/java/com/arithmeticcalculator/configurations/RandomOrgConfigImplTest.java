package com.arithmeticcalculator.configurations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = RandomOrgConfigImpl.class)
class RandomOrgConfigImplTest {

  @Autowired private RandomOrgConfigImpl randomOrgConfig;

  @Test
  void test() {
    assertEquals("https://www.test.org", randomOrgConfig.getHost());
    assertEquals(
        "/strings/?num=1&len=20&digits=on&upperalpha=on&loweralpha=on&unique=on&format=plain&rnd=new",
        randomOrgConfig.getStrings());
    assertEquals(
        "https://www.test.org/strings/?num=1&len=20&digits=on&upperalpha=on&loweralpha=on&unique=on&format=plain&rnd=new",
        randomOrgConfig.getUrl());
  }
}
