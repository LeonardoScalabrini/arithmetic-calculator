package com.arithmeticcalculator.api.v1.controllers;

import static com.arithmeticcalculator.fixtures.Fixture.fromJson;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.arithmeticcalculator.api.v1.dtos.UserCreateRequestDTO;
import com.arithmeticcalculator.ports.in.CreateUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @MockBean private CreateUserService createUserService;
  @Autowired private MockMvc mockMvc;

  @Test
  void create() throws Exception {
    var dto = new UserCreateRequestDTO("email", "password");
    doNothing().when(createUserService).create(dto.getEmail(), dto.getPassword());
    mockMvc
        .perform(
            post("/api/v1/user").content(fromJson(dto)).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
    verify(createUserService, times(1)).create(dto.getEmail(), dto.getPassword());
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
    verify(createUserService, times(0)).create(anyString(), anyString());
  }
}
