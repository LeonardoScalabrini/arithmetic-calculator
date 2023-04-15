package com.arithmeticcalculator.api.v1.controllers;

import com.arithmeticcalculator.api.v1.dtos.UserCreateRequestDTO;
import com.arithmeticcalculator.security.interfaces.SecurityService;
import com.arithmeticcalculator.usercases.interfaces.InitialBalanceUserCase;
import javax.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/user")
public final class UserController {

  private final SecurityService securityService;

  private final InitialBalanceUserCase initialBalanceUserCase;

  @Autowired
  public UserController(
      @NonNull SecurityService securityService,
      @NonNull InitialBalanceUserCase initialBalanceUserCase) {
    this.securityService = securityService;
    this.initialBalanceUserCase = initialBalanceUserCase;
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public @ResponseBody void create(@Valid @RequestBody @NonNull UserCreateRequestDTO dto) {
    securityService.createUser(dto.getEmail(), dto.getPassword());
    initialBalanceUserCase.apply(dto.getEmail());
  }
}
