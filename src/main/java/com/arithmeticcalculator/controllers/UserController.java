package com.arithmeticcalculator.controllers;

import com.arithmeticcalculator.dtos.UserCreateRequestDTO;
import com.arithmeticcalculator.security.interfaces.SecurityService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
public class UserController {

  private final SecurityService securityService;

  @Autowired
  public UserController(SecurityService securityService) {
    this.securityService = securityService;
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public @ResponseBody void create(@Valid @RequestBody UserCreateRequestDTO dto) {
    securityService.createUser(dto.getEmail(), dto.getPassword());
  }
}
