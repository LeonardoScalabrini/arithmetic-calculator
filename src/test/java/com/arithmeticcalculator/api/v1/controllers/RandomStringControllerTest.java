package com.arithmeticcalculator.api.v1.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.fixtures.Fixture;
import com.arithmeticcalculator.usercases.interfaces.PayOperationUserCase;
import com.arithmeticcalculator.usercases.interfaces.RandomStringUserCase;
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
  @MockBean private RandomStringUserCase randomStringUserCase;
  @MockBean private PayOperationUserCase payOperationUserCase;

  @BeforeEach
  void setUp() throws OperationException {
    when(payOperationUserCase.<String>payOperation(anyString(), any()))
        .thenReturn(Record.<String>from(Fixture.getUser(), Fixture.getOperation(), "result"));
  }

  @WithMockUser
  @Test
  void randomString() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/operations/random-string"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    verify(payOperationUserCase, times(1)).payOperation(anyString(), eq(randomStringUserCase));
  }

  @WithMockUser
  @Test
  void shouldThrowException() throws Exception {
    when(payOperationUserCase.payOperation(anyString(), eq(randomStringUserCase)))
        .thenThrow(OperationException.class);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/operations/random-string"))
        .andExpect(status().isInternalServerError());
    verify(payOperationUserCase, times(1)).payOperation(anyString(), eq(randomStringUserCase));
  }

  @WithAnonymousUser
  @Test
  void shouldAuth() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/operations/random-string"))
        .andExpect(status().isUnauthorized());
    verify(payOperationUserCase, times(0)).payOperation(anyString(), eq(randomStringUserCase));
  }
}
