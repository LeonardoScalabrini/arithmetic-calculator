package com.arithmeticcalculator.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arithmeticcalculator.domains.Operations;
import com.arithmeticcalculator.domains.commands.StringGeneratorCommand;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.domains.interfaces.PayOperationUserCase;
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

@WebMvcTest(StringGeneratorController.class)
class StringGeneratorControllerTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private StringGeneratorCommand stringGeneratorCommand;
  @MockBean private PayOperationUserCase payOperationUserCase;

  @BeforeEach
  void setUp() throws OperationException {
    when(payOperationUserCase.payOperation(anyString(), eq(Operations.RANDOM_STRING), any()))
        .thenReturn("random");
  }

  @WithMockUser
  @Test
  void randomString() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/operations/random-string"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isString());
    verify(payOperationUserCase, times(1))
        .payOperation(anyString(), eq(Operations.RANDOM_STRING), eq(stringGeneratorCommand));
  }

  @WithMockUser
  @Test
  void shouldThrowException() throws Exception {
    when(payOperationUserCase.payOperation(
            anyString(), eq(Operations.RANDOM_STRING), eq(stringGeneratorCommand)))
        .thenThrow(OperationException.class);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/operations/random-string"))
        .andExpect(status().isInternalServerError());
    verify(payOperationUserCase, times(1))
        .payOperation(anyString(), eq(Operations.RANDOM_STRING), eq(stringGeneratorCommand));
  }

  @WithAnonymousUser
  @Test
  void shouldAuth() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/operations/random-string"))
        .andExpect(status().isUnauthorized());
    verify(payOperationUserCase, times(0))
        .payOperation(anyString(), eq(Operations.RANDOM_STRING), eq(stringGeneratorCommand));
  }
}
