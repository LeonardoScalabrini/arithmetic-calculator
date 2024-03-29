package com.arithmeticcalculator.api.v1.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arithmeticcalculator.domains.commands.SquareRootCommand;
import com.arithmeticcalculator.fixtures.Fixture;
import com.arithmeticcalculator.ports.in.PayCostOperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(SquareRootController.class)
class SquareRootControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private PayCostOperationService payCostOperationService;

  @BeforeEach
  void setUp() {
    when(payCostOperationService.payOperation(anyString(), any(SquareRootCommand.class)))
        .thenReturn(Fixture.getRecord());
  }

  @WithMockUser
  @Test
  void sqrt() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/v1/operations/square-root/9"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists());

    verify(payCostOperationService, times(1)).payOperation(anyString(), any());
  }

  @WithMockUser
  @Test
  void shouldThrowException() throws Exception {
    when(payCostOperationService.payOperation(anyString(), any(SquareRootCommand.class)))
        .thenThrow(IllegalStateException.class);
    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/v1/operations/square-root/-1"))
        .andExpect(status().isInternalServerError());
    verify(payCostOperationService, times(0)).payOperation(anyString(), any());
  }

  @WithAnonymousUser
  @Test
  void shouldAuth() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/v1/operations/square-root/-1"))
        .andExpect(status().isUnauthorized());
    verify(payCostOperationService, times(0)).payOperation(anyString(), any());
  }
}
