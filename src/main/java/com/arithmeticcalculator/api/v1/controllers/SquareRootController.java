package com.arithmeticcalculator.api.v1.controllers;

import com.arithmeticcalculator.api.v1.dtos.RecordResponseDTO;
import com.arithmeticcalculator.domains.commands.SquareRootCommand;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.usercases.interfaces.PayOperationUserCase;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/operations")
public class SquareRootController {
  private final PayOperationUserCase payOperationUserCase;

  @Autowired
  public SquareRootController(PayOperationUserCase payOperationUserCase) {
    this.payOperationUserCase = payOperationUserCase;
  }

  @PostMapping("/square-root/{radicand}")
  public @ResponseBody ResponseEntity<RecordResponseDTO> sqrt(
      @Valid @PathVariable(value = "radicand") double radicand, Principal principal)
      throws OperationException {
    return ResponseEntity.ok(
        RecordResponseDTO.from(
            payOperationUserCase.payOperation(
                principal.getName(), SquareRootCommand.of(radicand))));
  }
}
