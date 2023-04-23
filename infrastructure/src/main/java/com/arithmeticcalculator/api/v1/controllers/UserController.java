package com.arithmeticcalculator.api.v1.controllers;

import com.arithmeticcalculator.api.v1.dtos.UserCreateRequestDTO;
import com.arithmeticcalculator.ports.in.CreateUserService;
import javax.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/user")
public final class UserController {

  private final CreateUserService createUserService;

  @Autowired
  public UserController(@NonNull CreateUserService createUserService) {
    this.createUserService = createUserService;
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public @ResponseBody void create(@Valid @RequestBody @NonNull UserCreateRequestDTO dto) {
    createUserService.create(dto.getEmail(), dto.getPassword());
  }
}
