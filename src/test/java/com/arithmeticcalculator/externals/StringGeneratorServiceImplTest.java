package com.arithmeticcalculator.externals;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.arithmeticcalculator.configurations.interfaces.RandomOrgConfig;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

@SpringBootTest
@WireMockTest(httpPort = 7777)
class StringGeneratorServiceImplTest {

  private static final String TEST_URL =
      "/strings/?num=1&len=20&digits=on&upperalpha=on&loweralpha=on&unique=on&format=plain&rnd=new";
  @Autowired public StringGeneratorServiceImpl stringGeneratorService;
  @MockBean public RandomOrgConfig randomOrgConfig;

  @BeforeEach
  void setUp() {
    when(randomOrgConfig.getHost()).thenReturn("http://localhost:7777");
  }

  @Test
  void stringGenerator() {
    final String expected = "5YGO1lItuvMQQWzuy0Qp";
    stubFor(
        get(urlEqualTo(TEST_URL))
            .willReturn(
                aResponse()
                    .withStatus(HttpStatus.OK.value())
                    .withHeader("Content-Type", "text/plain;charset=UTF-8")
                    .withBody(expected)));
    var result = stringGeneratorService.stringGenerator();
    assertEquals(expected, result.orElseThrow());
  }

  @Test
  void stringGeneratorWithNoContent() {
    stubFor(
        get(urlEqualTo(TEST_URL))
            .willReturn(
                aResponse()
                    .withStatus(HttpStatus.NO_CONTENT.value())
                    .withHeader("Content-Type", "text/plain;charset=UTF-8")));
    var result = stringGeneratorService.stringGenerator();
    assertTrue(result.isEmpty());
  }

  @Test
  void stringGeneratorWithError() {
    stubFor(
        get(urlEqualTo(TEST_URL))
            .willReturn(
                aResponse()
                    .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .withHeader("Content-Type", "text/plain;charset=UTF-8")));
    var result = stringGeneratorService.stringGenerator();
    assertTrue(result.isEmpty());
  }
}
