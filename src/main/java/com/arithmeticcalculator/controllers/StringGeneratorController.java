package com.arithmeticcalculator.controllers;

import com.arithmeticcalculator.domains.Operations;
import com.arithmeticcalculator.domains.commands.StringGeneratorCommand;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.domains.interfaces.PayOperationUserCase;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/operations")
public class StringGeneratorController {
  private final StringGeneratorCommand stringGeneratorCommand;
  private final PayOperationUserCase payOperationUserCase;

  @Autowired
  public StringGeneratorController(
      StringGeneratorCommand stringGeneratorCommand, PayOperationUserCase payOperationUserCase) {
    this.stringGeneratorCommand = stringGeneratorCommand;
    this.payOperationUserCase = payOperationUserCase;
  }

  @GetMapping("/random-string")
  public @ResponseBody ResponseEntity<String> randomString(Principal principal)
      throws OperationException {
    return ResponseEntity.ok(
        payOperationUserCase.payOperation(
            principal.getName(), Operations.RANDOM_STRING, stringGeneratorCommand));
  }
}
