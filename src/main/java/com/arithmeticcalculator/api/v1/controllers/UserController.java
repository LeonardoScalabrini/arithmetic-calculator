package com.arithmeticcalculator.api.v1.controllers;

import com.arithmeticcalculator.domains.interfaces.InitialBalanceUserCase;
import com.arithmeticcalculator.dtos.UserCreateRequestDTO;
import com.arithmeticcalculator.security.interfaces.SecurityService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/user")
public class UserController {

  private final SecurityService securityService;

  private final InitialBalanceUserCase initialBalanceUserCase;

  @Autowired
  public UserController(
      SecurityService securityService, InitialBalanceUserCase initialBalanceUserCase) {
    this.securityService = securityService;
    this.initialBalanceUserCase = initialBalanceUserCase;
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public @ResponseBody void create(@Valid @RequestBody UserCreateRequestDTO dto) {
    securityService.createUser(dto.getEmail(), dto.getPassword());
    initialBalanceUserCase.apply(dto.getEmail());
  }
}
