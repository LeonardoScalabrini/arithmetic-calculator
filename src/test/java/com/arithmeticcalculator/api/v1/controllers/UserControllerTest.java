package com.arithmeticcalculator.api.v1.controllers;

import static com.arithmeticcalculator.fixtures.Fixture.fromJson;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.arithmeticcalculator.domains.interfaces.InitialBalanceUserCase;
import com.arithmeticcalculator.dtos.UserCreateRequestDTO;
import com.arithmeticcalculator.security.interfaces.SecurityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @MockBean private SecurityService securityService;
  @MockBean private InitialBalanceUserCase initialBalanceUserCase;
  @Autowired private MockMvc mockMvc;

  @Test
  void create() throws Exception {
    var dto = new UserCreateRequestDTO("email", "password");
    doNothing().when(securityService).createUser(dto.getEmail(), dto.getPassword());
    doNothing().when(initialBalanceUserCase).apply(dto.getEmail());
    mockMvc
        .perform(
            post("/api/v1/user").content(fromJson(dto)).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
    verify(securityService, times(1)).createUser(dto.getEmail(), dto.getPassword());
    verify(initialBalanceUserCase, times(1)).apply(dto.getEmail());
  }

  @Test
  void badRequest() throws Exception {
    mockMvc
        .perform(post("/api/v1/user").content("").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    mockMvc
        .perform(
            post("/api/v1/user")
                .content(fromJson(new UserCreateRequestDTO("", "")))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    mockMvc
        .perform(
            post("/api/v1/user")
                .content(fromJson(new UserCreateRequestDTO("email", "")))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    mockMvc
        .perform(
            post("/api/v1/user")
                .content(fromJson(new UserCreateRequestDTO("", "password")))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    verify(securityService, times(0)).createUser(anyString(), anyString());
    verify(initialBalanceUserCase, times(0)).apply(anyString());
  }
}
