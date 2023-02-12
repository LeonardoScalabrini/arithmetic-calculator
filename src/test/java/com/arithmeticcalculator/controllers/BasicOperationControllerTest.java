package com.arithmeticcalculator.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BasicOperationController.class)
class BasicOperationControllerTest {
  @Autowired private MockMvc mockMvc;

  @Test
  void calc() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/operations/addtion?n1=1.0&n2=2.0"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNumber());
  }

  @Test
  void shouldValidateEnum() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/operations/notexist?n1=1.0&n2=2.0"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void shouldValidateNumbers() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/operations/addtion?n1=abc&n2=2.0"))
        .andExpect(status().isBadRequest());
    mockMvc
        .perform(MockMvcRequestBuilders.post("/operations/addtion?n1=1.0&n2=abc"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void shouldThrowException() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/operations/division?n1=1.0&n2=0"))
        .andExpect(status().isInternalServerError());
  }
}
