package com.arithmeticcalculator.api.v1.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class UserCreateRequestDTO implements Serializable {
  @NotBlank(message = "E-mail is required")
  private final String email;

  @NotBlank(message = "Password is required")
  private final String password;

  @JsonCreator
  public UserCreateRequestDTO(
      @NonNull @JsonProperty("email") String email,
      @NonNull @JsonProperty("password") String password) {
    this.email = email;
    this.password = password;
  }
}
