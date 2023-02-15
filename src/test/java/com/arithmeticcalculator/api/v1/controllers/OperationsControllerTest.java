package com.arithmeticcalculator.api.v1.controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arithmeticcalculator.domains.Operations;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.fixtures.Fixture;
import com.arithmeticcalculator.usercases.interfaces.PayOperationUserCase;
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

@WebMvcTest(BasicOperationController.class)
class OperationsControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private PayOperationUserCase payOperationUserCase;

  @BeforeEach
  void setUp() throws OperationException {
    when(payOperationUserCase.<Double>payOperation(anyString(), eq(Operations.ADDTION), any()))
        .thenReturn(Fixture.getRecord());
  }

  @WithMockUser
  @Test
  void calc() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/v1/operations/addtion?n1=1.0&n2=2.0"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    verify(payOperationUserCase, times(1)).payOperation(anyString(), eq(Operations.ADDTION), any());
  }

  @WithMockUser
  @Test
  void shouldValidateEnum() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/v1/operations/notexist?n1=1.0&n2=2.0"))
        .andExpect(status().isInternalServerError());
    verify(payOperationUserCase, times(0)).payOperation(anyString(), any(Operations.class), any());
  }

  @WithMockUser
  @Test
  void shouldValidateNumbers() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/v1/operations/addtion?n1=abc&n2=2.0"))
        .andExpect(status().isBadRequest());
    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/v1/operations/addtion?n1=1.0&n2=abc"))
        .andExpect(status().isBadRequest());
    verify(payOperationUserCase, times(0)).payOperation(anyString(), any(), any());
  }

  @WithMockUser
  @Test
  void shouldThrowException() throws Exception {
    when(payOperationUserCase.payOperation(anyString(), eq(Operations.DIVISION), any()))
        .thenThrow(OperationException.class);
    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/v1/operations/division?n1=1.0&n2=0"))
        .andExpect(status().isInternalServerError());
    verify(payOperationUserCase, times(1)).payOperation(anyString(), any(), any());
  }

  @WithAnonymousUser
  @Test
  void shouldAuth() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/v1/operations/division?n1=1.0&n2=0"))
        .andExpect(status().isUnauthorized());
    verify(payOperationUserCase, times(0)).payOperation(anyString(), any(), any());
  }
}
