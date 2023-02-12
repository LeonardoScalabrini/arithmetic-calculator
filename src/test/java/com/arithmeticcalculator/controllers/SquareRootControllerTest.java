package com.arithmeticcalculator.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(SquareRootController.class)
class SquareRootControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void sqrt() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/operations/square-root/9"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNumber());
  }

  @Test
  void shouldThrowException() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/operations/square-root/-1"))
        .andExpect(status().isInternalServerError());
  }
}
