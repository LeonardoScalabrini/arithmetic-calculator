package com.arithmeticcalculator.controllers;

import com.arithmeticcalculator.domains.Operations;
import com.arithmeticcalculator.domains.commands.SquareRootCommand;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.domains.interfaces.PayOperationUserCase;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/operations")
public class SquareRootController {
  private final PayOperationUserCase payOperationUserCase;

  @Autowired
  public SquareRootController(PayOperationUserCase payOperationUserCase) {
    this.payOperationUserCase = payOperationUserCase;
  }

  @PostMapping("/square-root/{radicand}")
  public @ResponseBody ResponseEntity<Double> sqrt(
      @Valid @PathVariable(value = "radicand") double radicand, Principal principal)
      throws OperationException {
    return ResponseEntity.ok(
        payOperationUserCase.payOperation(
            principal.getName(), Operations.SQUARE_ROOT, SquareRootCommand.of(radicand)));
  }
}
