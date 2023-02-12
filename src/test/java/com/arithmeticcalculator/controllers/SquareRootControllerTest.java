package com.arithmeticcalculator.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(SquareRootController.class)
class SquareRootControllerTest {

  @Autowired private MockMvc mockMvc;

  @WithMockUser
  @Test
  void sqrt() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/operations/square-root/9"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNumber());
  }

  @WithMockUser
  @Test
  void shouldThrowException() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/operations/square-root/-1"))
        .andExpect(status().isInternalServerError());
  }

  @WithAnonymousUser
  @Test
  void shouldAuth() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/operations/square-root/-1"))
        .andExpect(status().isUnauthorized());
  }
}
