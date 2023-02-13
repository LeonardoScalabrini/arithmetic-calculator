package com.arithmeticcalculator.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arithmeticcalculator.fixtures.Fixture;
import com.arithmeticcalculator.queries.interfaces.RecordPaginationQuery;
import java.util.Collections;
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

@WebMvcTest(QueryController.class)
class QueryControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private RecordPaginationQuery recordPaginationQuery;

  @BeforeEach
  void setUp() {
    when(recordPaginationQuery.findBy(1, 2))
        .thenReturn(Collections.singletonList(Fixture.getRecordEntity()));
  }

  @WithMockUser
  @Test
  void findBy() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/records/search?page=1&size=2"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    verify(recordPaginationQuery, times(1)).findBy(1, 2);
  }

  @WithMockUser
  @Test
  void noContent() throws Exception {
    when(recordPaginationQuery.findBy(1, 2)).thenReturn(Collections.emptyList());
    mockMvc
        .perform(MockMvcRequestBuilders.get("/records/search?page=1&size=2"))
        .andExpect(status().isNoContent())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    verify(recordPaginationQuery, times(1)).findBy(1, 2);
  }

  @WithAnonymousUser
  @Test
  void shouldAuth() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/records/search?page=1&size=2"))
        .andExpect(status().isUnauthorized());
    verify(recordPaginationQuery, times(0)).findBy(1, 2);
  }
}
