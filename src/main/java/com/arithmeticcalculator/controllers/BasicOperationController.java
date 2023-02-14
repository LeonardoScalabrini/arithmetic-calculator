package com.arithmeticcalculator.controllers;

import com.arithmeticcalculator.domains.Operations;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.domains.factories.BasicOperationCommandFactory;
import com.arithmeticcalculator.domains.interfaces.PayOperationUserCase;
import java.security.Principal;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/operations")
public class BasicOperationController {

  private final PayOperationUserCase payOperationUserCase;

  @Autowired
  public BasicOperationController(PayOperationUserCase payOperationUserCase) {
    this.payOperationUserCase = payOperationUserCase;
  }

  @PostMapping("/{operation}")
  public @ResponseBody ResponseEntity<Double> calc(
      @Valid @PathVariable(value = "operation") @NotBlank String operation,
      @Valid @RequestParam("n1") double n1,
      @Valid @RequestParam("n2") double n2,
      Principal principal)
      throws OperationException {
    try {
      var operations = Operations.valueOf(operation.toUpperCase());
      var command = BasicOperationCommandFactory.of(operations, n1, n2);
      return ResponseEntity.ok(
          payOperationUserCase.payOperation(principal.getName(), operations, command));
    } catch (IllegalArgumentException e) {
      throw OperationException.withMessage("Operation should be valid!");
    }
  }
}
