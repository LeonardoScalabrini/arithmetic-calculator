package com.arithmeticcalculator.api.v1.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.*;

@Value
public class UserCreateRequestDTO {
  @NotBlank(message = "E-mail is required")
  String email;

  @NotBlank(message = "Password is required")
  String password;

  @JsonCreator
  public UserCreateRequestDTO(
      @NonNull @JsonProperty("email") String email,
      @NonNull @JsonProperty("password") String password) {
    this.email = email;
    this.password = password;
  }
}
