package com.arithmeticcalculator.api.v1.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.fixtures.Fixture;
import com.arithmeticcalculator.ports.in.PayOperationService;
import com.arithmeticcalculator.ports.in.RandomStringService;
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

@WebMvcTest(RandomStringController.class)
class RandomStringControllerTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private RandomStringService randomStringService;
  @MockBean private PayOperationService payOperationService;

  @BeforeEach
  void setUp() {
    when(payOperationService.<String>payOperation(anyString(), any()))
        .thenReturn(Record.<String>from(Fixture.getUser(), Fixture.getCostOperation(), "result"));
  }

  @WithMockUser
  @Test
  void randomString() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/operations/random-string"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    verify(payOperationService, times(1)).payOperation(anyString(), eq(randomStringService));
  }

  @WithMockUser
  @Test
  void shouldThrowException() throws Exception {
    when(payOperationService.payOperation(anyString(), eq(randomStringService)))
        .thenThrow(IllegalStateException.class);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/operations/random-string"))
        .andExpect(status().isInternalServerError());
    verify(payOperationService, times(1)).payOperation(anyString(), eq(randomStringService));
  }

  @WithAnonymousUser
  @Test
  void shouldAuth() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/operations/random-string"))
        .andExpect(status().isUnauthorized());
    verify(payOperationService, times(0)).payOperation(anyString(), eq(randomStringService));
  }
}
