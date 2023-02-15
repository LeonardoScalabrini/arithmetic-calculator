package com.arithmeticcalculator.externals;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import com.arithmeticcalculator.configurations.interfaces.RandomOrgConfig;
import com.arithmeticcalculator.externals.exceptions.RandomOrgException;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

@SpringBootTest
@WireMockTest(httpPort = 7777)
class RandomOrgServiceImplTest {

  private static final String TEST_URL =
      "/strings/?num=1&len=20&digits=on&upperalpha=on&loweralpha=on&unique=on&format=plain&rnd=new";
  @Autowired public RandomOrgServiceImpl randomOrgService;
  @MockBean public RandomOrgConfig randomOrgConfig;

  @BeforeEach
  void setUp() {
    when(randomOrgConfig.getHost()).thenReturn("http://localhost:7777");
    when(randomOrgConfig.getStrings()).thenReturn(TEST_URL);
  }

  @Test
  void strings() throws RandomOrgException {
    final String expected = "5YGO1lItuvMQQWzuy0Qp";
    stubFor(
        get(urlEqualTo(TEST_URL))
            .willReturn(
                aResponse()
                    .withStatus(HttpStatus.OK.value())
                    .withHeader("Content-Type", "text/plain;charset=UTF-8")
                    .withBody(expected)));
    var result = randomOrgService.strings();
    Assertions.assertEquals(expected, result);
  }

  @Test
  void stringsWithNoContent() {
    stubFor(
        get(urlEqualTo(TEST_URL))
            .willReturn(
                aResponse()
                    .withStatus(HttpStatus.NO_CONTENT.value())
                    .withHeader("Content-Type", "text/plain;charset=UTF-8")));
    assertThrows(RandomOrgException.class, () -> randomOrgService.strings());
  }

  @Test
  void stringsWithError() {
    stubFor(
        get(urlEqualTo(TEST_URL))
            .willReturn(
                aResponse()
                    .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .withHeader("Content-Type", "text/plain;charset=UTF-8")));
    assertThrows(RandomOrgException.class, () -> randomOrgService.strings());
  }
}
