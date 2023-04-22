package com.arithmeticcalculator.api.v1.controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arithmeticcalculator.fixtures.Fixture;
import com.arithmeticcalculator.ports.in.PayOperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BasicOperationController.class)
class BasicOperationsControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private PayOperationService payOperationService;

  @BeforeEach
  void setUp() {
    when(payOperationService.<Double>payOperation(anyString(), any()))
        .thenReturn(Fixture.getRecord());
  }

  @WithMockUser
  @ParameterizedTest
  @CsvSource({
    "/api/v1/operations/addition?n1=1.0&n2=2.0",
    "/api/v1/operations/subtraction?n1=1.0&n2=2.0",
    "/api/v1/operations/multiplication?n1=1.0&n2=2.0",
    "/api/v1/operations/division?n1=1.0&n2=2.0",
  })
  void calc(String url) throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post(url))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    verify(payOperationService, times(1)).payOperation(anyString(), any());
  }

  @WithMockUser
  @ParameterizedTest
  @CsvSource({
    "/api/v1/operations/addition?n1=abc&n2=2.0",
    "/api/v1/operations/subtraction?n1=abc&n2=2.0",
    "/api/v1/operations/multiplication?n1=abc&n2=2.0",
    "/api/v1/operations/division?n1=abc&n2=2.0",
    "/api/v1/operations/addition?n1=1.0&n2=abc",
    "/api/v1/operations/subtraction?n1=1.0&n2=abc",
    "/api/v1/operations/multiplication?n1=1.0&n2=abc",
    "/api/v1/operations/division?n1=1.0&n2=abc",
  })
  void shouldValidateNumbers(String url) throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(url)).andExpect(status().isBadRequest());
    verify(payOperationService, times(0)).payOperation(anyString(), any());
  }

  @WithMockUser
  @ParameterizedTest
  @CsvSource({
    "/api/v1/operations/addition?n1=1.0&n2=2.0",
    "/api/v1/operations/subtraction?n1=1.0&n2=2.0",
    "/api/v1/operations/multiplication?n1=1.0&n2=2.0",
    "/api/v1/operations/division?n1=1.0&n2=2.0",
  })
  void shouldThrowException(String url) throws Exception {
    when(payOperationService.payOperation(anyString(), any()))
        .thenThrow(IllegalStateException.class);
    mockMvc.perform(MockMvcRequestBuilders.post(url)).andExpect(status().isInternalServerError());
    verify(payOperationService, times(1)).payOperation(anyString(), any());
  }

  @WithAnonymousUser
  @ParameterizedTest
  @CsvSource({
    "/api/v1/operations/addition?n1=1.0&n2=2.0",
    "/api/v1/operations/subtraction?n1=1.0&n2=2.0",
    "/api/v1/operations/multiplication?n1=1.0&n2=2.0",
    "/api/v1/operations/division?n1=1.0&n2=2.0",
  })
  void shouldAuth(String url) throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(url)).andExpect(status().isUnauthorized());
    verify(payOperationService, times(0)).payOperation(anyString(), any());
  }
}
