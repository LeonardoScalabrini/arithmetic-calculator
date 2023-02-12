package com.arithmeticcalculator.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arithmeticcalculator.domains.interfaces.RandomService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(RandomStringController.class)
class RandomStringControllerTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private RandomService randomService;

  @BeforeEach
  void setUp() {
    when(randomService.stringGenerator()).thenReturn(Optional.of("test"));
    ;
  }

  @Test
  void randomString() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/operations/random-string"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isString());
    verify(randomService, times(1)).stringGenerator();
  }

  @Test
  void shouldThrowException() throws Exception {
    doThrow(RuntimeException.class).when(randomService).stringGenerator();
    mockMvc
        .perform(MockMvcRequestBuilders.get("/operations/random-string"))
        .andExpect(status().isInternalServerError());
    verify(randomService, times(1)).stringGenerator();
  }
}
